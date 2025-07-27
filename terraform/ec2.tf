resource "aws_security_group" "ssh_access" {
  name        = "ssh_access"
  description = "Allow SSH, HTTP, and App access from anywhere"

  ingress {
    description = "SSH"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "HTTP"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "Custom app port"
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    description = "Allow all outbound traffic"
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "app_server" {
  ami           = "ami-0083ee179c14acc6a"
  instance_type = var.instance_type
  key_name      = aws_key_pair.deployer.key_name
  vpc_security_group_ids = [aws_security_group.ssh_access.id]

  user_data = <<-EOF
              #!/bin/bash
              set -eux

              # Aktualizacja systemu i instalacja Dockera
              apt-get update -y
              apt-get install -y docker.io docker-compose

              # Tworzenie użytkownika castme
              useradd -m -s /bin/bash castme

              # Tworzenie katalogu aplikacji
              mkdir -p /srv/app
              chown castme:castme /srv/app

              # Tworzenie katalogu .ssh i ustawienie uprawnień
              mkdir -p /home/castme/.ssh
              chown castme:castme /home/castme/.ssh
              chmod 700 /home/castme/.ssh

              # Wstawienie publicznego klucza SSH (podmieniamy wartość niżej)
              echo "${file("castme")}" > /home/castme/.ssh/id_rsa
              echo "${file("castme.pub")}" > /home/castme/.ssh/id_rsa.pub
              chown castme:castme /home/castme/.ssh/id_rsa
              chown castme:castme /home/castme/.ssh/id_rsa.pub
              chmod 600 /home/castme/.ssh/id_rsa
              chmod 644 /home/castme/.ssh/id_rsa.pub

              ssh-keyscan github.com >> /home/castme/.ssh/known_hosts
              chown castme:castme /home/castme/.ssh/known_hosts
              chmod 644 /home/castme/.ssh/known_hosts

              sudo -u castme git clone git@github.com:stepnicky/CastMe.git /srv/app

              # Dodanie użytkownika do grupy docker
              usermod -aG docker castme

              # Automatyczny start dockera
              systemctl enable docker

              cd /srv/app
              sudo -u castme git fetch origin
              sudo -u castme git checkout feature/deployment
              sudo -u castme git pull origin feature/deployment
              sudo -u castme docker-compose up --build -d
            EOF

  tags = {
    Name = "codewiz-ec2"
  }
}
