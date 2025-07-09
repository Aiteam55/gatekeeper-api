pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        SSH_KEY_ID = 'gatekeeper-ssh-key'
        EC2_HOST = '13.60.236.205'
        EC2_USERNAME = 'ec2-user'
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
                        ssh -o StrictHostKeyChecking=no ${REMOTE_USER}@${EC2_HOST} "echo 'SSH connection successful!' && hostname && whoami"
                    """
                }
            }
        }
    }
}