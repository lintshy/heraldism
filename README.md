# Heraldism - Dota 2 Service

A comprehensive Dota 2 player and match management service with AI-powered draft advice.

## 🎮 Features

- **Player Management**: Register and manage Dota 2 players
- **Match Tracking**: Fetch and store match data from OpenDota API
- **Performance Scoring**: Calculate detailed performance metrics for matches
- **Draft Advice**: AI-powered hero recommendations using RAG/LangChain
- **Notifications**: Discord/Telegram notifications for match results
- **Idempotency**: Prevent duplicate operations with DynamoDB-based idempotency store

## 🏗️ Architecture

This service follows **Hexagonal Architecture** (Ports and Adapters) with clean separation of concerns:

```
├── api/                    # Inbound adapters (REST controllers)
├── application/            # Use cases (business logic)
├── domain/                 # Core domain entities and ports
├── infrastructure/         # Outbound adapters (DynamoDB, HTTP clients)
├── common/                 # Cross-cutting concerns (DTOs, errors, utils)
└── config/                 # Configuration beans
```

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker (for local DynamoDB)
- AWS Account (for production deployment)

### Local Development

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/heraldism.git
   cd heraldism
   ```

2. **Start local DynamoDB**
   ```bash
   docker run -p 8000:8000 amazon/dynamodb-local
   ```

3. **Build the project**
   ```bash
   make build
   # or
   mvn clean package
   ```

4. **Run the application**
   ```bash
   make run
   # or
   mvn spring-boot:run
   ```

5. **Access Swagger UI**
   ```
   http://localhost:8080/api/swagger-ui.html
   ```

## 📦 Tech Stack

- **Framework**: Spring Boot 3.2.0
- **Database**: AWS DynamoDB
- **API Client**: WebClient (Spring WebFlux)
- **Mapping**: MapStruct
- **Documentation**: SpringDoc OpenAPI
- **Testing**: JUnit 5, Mockito, Testcontainers
- **Infrastructure**: Terraform, Kubernetes

## 🔧 Configuration

Configuration files are located in the `config/` directory:

- `application.yml` - Base configuration
- `application-dev.yml` - Development environment
- `application-prod.yml` - Production environment

### Environment Variables

```bash
# AWS Configuration
AWS_REGION=us-east-1

# OpenDota API
OPENDOTA_BASE_URL=https://api.opendota.com/api

# RAG Service
RAG_SERVICE_URL=http://localhost:8081

# Discord Webhook
DISCORD_WEBHOOK_URL=your-webhook-url
```

## 🧪 Testing

```bash
# Run all tests
make test

# Run specific test
mvn test -Dtest=PlayersControllerTest
```

## 🚢 Deployment

### Using Terraform

```bash
cd infra/terraform
terraform init
terraform plan
terraform apply
```

### Using Docker

```bash
# Build Docker image
make docker-build

# Run container
make docker-run
```

### Using Kubernetes

```bash
kubectl apply -f infra/k8s/
```

## 📊 API Endpoints

### Players
- `POST /api/players` - Create a new player
- `GET /api/players` - List all players
- `GET /api/players/{id}` - Get player by ID
- `PUT /api/players/{id}` - Update player
- `DELETE /api/players/{id}` - Delete player

### Matches
- `POST /api/matches/refresh` - Refresh matches from OpenDota
- `GET /api/matches/{id}` - Get match details
- `POST /api/matches/{id}/score` - Compute match score
- `GET /api/matches/{id}/report` - Get match report

### Draft
- `POST /api/draft/advise` - Get draft advice
- `POST /api/draft/analyze` - Analyze draft composition

## 🗄️ Database Schema

### DynamoDB Tables

1. **players** - Player information
   - PK: `playerId`
   - GSI: `steamId`

2. **matches** - Match data
   - PK: `matchId`
   - SK: `timestamp`
   - GSI: `playerId` + `timestamp`

3. **match_scores** - Performance scores
   - PK: `matchId`
   - SK: `playerId`

4. **idempotency** - Idempotency tokens
   - PK: `idempotencyKey`
   - TTL: `expirationTime`

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Heraldism Team** - [support@heraldism.com](mailto:support@heraldism.com)

## 🙏 Acknowledgments

- [OpenDota API](https://docs.opendota.com/) for match data
- Spring Boot community
- AWS DynamoDB team
