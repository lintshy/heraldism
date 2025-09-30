terraform {
  required_version = ">= 1.0"
  
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
  
  backend "s3" {
    bucket = "dota-service-terraform-state"
    key    = "terraform.tfstate"
    region = "us-east-1"
  }
}

provider "aws" {
  region = var.aws_region
  
  default_tags {
    tags = {
      Project     = "dota-service"
      Environment = var.environment
      ManagedBy   = "Terraform"
    }
  }
}

# DynamoDB Tables Module
module "dynamodb" {
  source = "./modules/dynamodb"
  
  environment = var.environment
  project_name = var.project_name
}

# IAM Roles and Policies
resource "aws_iam_role" "dota_service_role" {
  name = "${var.project_name}-${var.environment}-role"
  
  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Principal = {
          Service = "ecs-tasks.amazonaws.com"
        }
      }
    ]
  })
}

# Outputs
output "dynamodb_tables" {
  value = module.dynamodb.table_names
  description = "Names of created DynamoDB tables"
}
