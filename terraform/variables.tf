variable "aws_region" {
  default = "eu-central-1"
}

variable "instance_type" {
  default = "t3.micro"
}

variable "key_name" {
  description = "Nazwa istniejącego klucza SSH (z `aws ec2 create-key-pair`)"
  type        = string
}
