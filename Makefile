.PHONY: help build test run clean docker-build docker-run deploy

# Variables
APP_NAME := dota-service
VERSION := 0.0.1-SNAPSHOT
DOCKER_IMAGE := $(APP_NAME):$(VERSION)
AWS_REGION := us-east-1
AWS_PROFILE := default

help: ## Show this help message
	@echo 'Usage: make [target]'
	@echo ''
	@echo 'Available targets:'
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "  %-15s %s\n", $$1, $$2}' $(MAKEFILE_LIST)

build: ## Build the application
	mvn clean package -DskipTests

test: ## Run tests
	mvn test

run: ## Run the application locally
	mvn spring-boot:run

clean: ## Clean build artifacts
	mvn clean
	rm -rf target/

docker-build: ## Build Docker image
	docker build -t $(DOCKER_IMAGE) .

docker-run: ## Run Docker container
	docker run -p 8080:8080 --env-file .env $(DOCKER_IMAGE)

terraform-init: ## Initialize Terraform
	cd infra/terraform && terraform init

terraform-plan: ## Plan Terraform changes
	cd infra/terraform && terraform plan

terraform-apply: ## Apply Terraform changes
	cd infra/terraform && terraform apply

terraform-destroy: ## Destroy Terraform resources
	cd infra/terraform && terraform destroy

deploy: build docker-build ## Build and deploy application
	@echo "Deploying $(APP_NAME) version $(VERSION)"
	# Add deployment commands here

logs: ## Show application logs
	docker logs -f $(APP_NAME)

lint: ## Run code linting
	mvn checkstyle:check

format: ## Format code
	mvn spotless:apply
