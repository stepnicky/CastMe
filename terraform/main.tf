provider "aws" {
  region = var.aws_region
}

resource "aws_key_pair" "deployer" {
  key_name   = var.key_name
  public_key = file(pathexpand("~/.ssh/github_actions_deploy.pub"))
}

locals {
  app_env = file("../.env")
}