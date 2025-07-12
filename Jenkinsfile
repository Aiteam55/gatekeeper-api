pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        SSH_KEY_ID = 'gatekeeper-ssh-key'
        EC2_HOST = '13.60.79.230'
        EC2_USERNAME = 'ec2-user'
        REMOTE_DIR = '/home/ec2-user/gatekeeper-api'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

         stage('Test SSH Access to EC2') {
            steps {
                sshagent(credentials: ["${SSH_KEY_ID}"]) {
                    sh """
                        echo "Testing connection to EC2..."
                        ssh -o StrictHostKeyChecking=no ${EC2_USERNAME}@${EC2_HOST} "echo 'SSH connection successful!' && hostname && whoami"
                    """
                }
            }
        }

        stage('Copy files to EC2') {
            steps {
                sshagent(credentials: ["${SSH_KEY_ID}"]) {
                     sh """
                        echo "Preparing remote directory..."
                        ssh -o StrictHostKeyChecking=no ${EC2_USERNAME}@${EC2_HOST} \\
                            'mkdir -p ${REMOTE_DIR}/target'

                        echo "Copying app files..."
                        scp -o StrictHostKeyChecking=no \\
                            target/*.jar \\
                            ${EC2_USERNAME}@${EC2_HOST}:${REMOTE_DIR}/target/

                        scp -o StrictHostKeyChecking=no \\
                            docker-compose.yml \\
                            Dockerfile \\
                            ${EC2_USERNAME}@${EC2_HOST}:${REMOTE_DIR}/
                    """
                }
            }
        }

         stage('Deploy on EC2') {
            steps {
                sshagent(credentials: ["${SSH_KEY_ID}"]) {
                    sh """
                        echo "Deploying on EC2 via Docker Compose..."
                        ssh -o StrictHostKeyChecking=no ${EC2_USERNAME}@${EC2_HOST} "
                            cd ${REMOTE_DIR} &&
                            docker-compose down || true &&
                            docker-compose up --build -d
                        "
                    """
                }
            }
        }
    }
}