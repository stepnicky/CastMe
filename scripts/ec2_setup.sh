#!/bin/bash

# Exit on any error
set -e

# Update system packages
echo "Updating system packages..."
sudo apt update && sudo apt upgrade -y

# Install Docker
echo "Installing Docker..."
curl -fsSL https://get.docker.com | sh
echo "Verifying Docker installation..."
docker --version

# sudo apt install docker-compose

# OPTIONAL: Move Docker data to external volume (uncomment and modify as needed)
# echo "Moving Docker data to external volume..."
# Refer to: https://www.guguweb.com/2019/02/07/how-to-move-docker-data-directory-to-another-location-on-ubuntu/

# OPTIONAL: Disable Apache if running
if systemctl is-active --quiet apache2; then
    echo "Disabling and stopping Apache..."
    sudo systemctl disable apache2
    sudo systemctl stop apache2
else
    echo "Apache is not running."
fi

# Install NGINX
echo "Installing NGINX..."
sudo apt install nginx -y

# Replacing SSL protocols
sudo sed -i 's/ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;/ssl_protocols TLSv1.2 TLSv1.3;/g' /etc/nginx/nginx.conf



# server_tokens off
if ! grep -q "server_tokens off;" /etc/nginx/nginx.conf; then
  sudo sed -i '/http {/a \\tserver_tokens off;' /etc/nginx/nginx.conf
  echo "Added: server_tokens off"
else
  echo "Already exists: server_tokens off"
fi

# ssl_ciphers ...
if ! grep -q "ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256" /etc/nginx/nginx.conf; then
  sudo sed -i '/http {/a \\tssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES256-GCM-SHA384:DHE-RSA-CHACHA20-POLY1305;' /etc/nginx/nginx.conf
  echo "Added: ssl_ciphers"
else
  echo "Already exists: ssl_ciphers"
fi

# Configure NGINX
echo "Configuring NGINX..."

sudo sed -i 's/ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;/ssl_protocols TLSv1.2 TLSv1.3;/g' /etc/nginx/nginx.conf

if ! grep -q "ssl_prefer_server_ciphers on;" /etc/nginx/nginx.conf; then
  sudo sed -i '/http {/a \\tssl_prefer_server_ciphers on;\n\tclient_max_body_size 100M;' /etc/nginx/nginx.conf
fi
if ! grep -q "server_tokens off;" /etc/nginx/nginx.conf; then
  sudo sed -i '/http {/a \\tserver_tokens off;' /etc/nginx/nginx.conf
fi
if ! grep -q "ssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256" /etc/nginx/nginx.conf; then
  sudo sed -i '/http {/a \\tssl_ciphers ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:DHE-RSA-AES128-GCM-SHA256:DHE-RSA-AES256-GCM-SHA384:DHE-RSA-CHACHA20-POLY1305;' /etc/nginx/nginx.conf
fi

# Disable default NGINX website
echo "Disabling default NGINX website..."
sudo rm -f /etc/nginx/sites-enabled/default
sudo rm -f /etc/nginx/sites-available/default

# Reload NGINX
echo "Reloading NGINX..."
sudo nginx -t && sudo systemctl reload nginx

# Install nfs tools
sudo apt install nfs-common -y

# Install Certbot
sudo apt install certbot python3-certbot-nginx -y

echo "Setup completed successfully!"

# Install mariadb client for local db dump loading
#sudo apt install unzip mariadb-client -y
