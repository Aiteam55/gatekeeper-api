#!/bin/bash

# Log all output to file
exec > /var/log/init-vps-install.log 2>&1
set -e

echo "Updating system..."
apt update && apt upgrade -y

echo "Installing Docker..."
apt install -y docker.io
usermod -aG docker ubuntu
newgrp docker
echo "Installing Docker Compose..."
apt install -y docker-compose

echo "Installing Git..."
apt install -y git

echo "VPS setup complete"
