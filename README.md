\# TDA Analysis Service

A Spring Boot microservice implementing Topological Data Analysis for screenplay evaluation using Clean Architecture principles.

\## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Theoretical Foundation](#theoretical-foundation)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Development](#development)
- [Testing](#testing)

\## 🎯 Overview

This service implements TDA (Topological Data Analysis) for screenplay evaluation using three core frameworks:
- Modal Logic Framework for creative analysis
- Topological Analysis Framework for feature extraction
- Pattern Analysis Framework for human-AI pattern differentiation

\### Key Features
- Clean Architecture implementation
- Mathematical analysis of screenplay structure
- Pattern recognition for AI/human content
- Creative process validation
- Comprehensive test coverage

\## 🏗 Architecture

This project follows Clean Architecture with four main layers:

\### 1. Domain Layer (Enterprise Business Rules)
- Location: `src/main/java/com/cleanarchitecture/domain`
- Contains:
  - Core entities
  - Value objects
  - Port interfaces
  - Domain exceptions

\### 2. Application Layer (Application Business Rules)
- Location: `src/main/java/com/cleanarchitecture/application`
- Contains:
  - Framework implementations
  - Application services
  - DTOs for internal use

\### 3. Infrastructure Layer (Frameworks & Drivers)
- Location: `src/main/java/com/cleanarchitecture/infrastructure`
- Contains:
  - JavaPlex integration
  - External service implementations
  - Mathematical computation utilities

\### 4. Presentation Layer (Interface Adapters)
- Location: `src/main/java/com/cleanarchitecture/presentation`
- Contains:
  - REST controllers
  - Request/Response models
  - API documentation

\## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── cleanarchitecture/
│   │           ├── domain/
│   │           │   ├── entity/
│   │           │   ├── valueobject/
│   │           │   ├── port/
│   │           │   │   ├── input/
│   │           │   │   └── output/
│   │           │   └── exception/
│   │           ├── application/
│   │           │   ├── service/
│   │           │   └── dto/
│   │           ├── infrastructure/
│   │           │   ├── persistence/
│   │           │   └── config/
│   │           └── presentation/
│   │               └── api/
│   │                   └── rest/
│   │                       ├── controller/
│   │                       ├── request/
│   │                       └── response/
│   └── resources/
│       └── application.yml
└── test/
    └── java/
        └── com/
            └── cleanarchitecture/
                ├── domain/
                ├── application/
                ├── infrastructure/
                └── presentation/
```

\## 🧮 Theoretical Foundation

\### Modal Logic Framework
Based on research by Farooq Khan et al. (2024):
- Creative Potential: ◇(A ∧ C)
- Human Creativity Foundation: □(H → ◇C)
- Collaborative Creation: ◇(A ∧ H ∧ C)

\### Topological Analysis Framework
Following Kushnareva et al. (2021):
- Feature Extraction: F: Text → R^n
- Persistence Diagram: PH(text) = {(b_i, d_i)}
- Quality Metrics: Q(text) = (β₀, β₁, β₂, W)

\### Pattern Analysis Framework
Based on Papia et al. (2023):
- Human patterns: More varied topological structures
- AI patterns: More regular and predictable patterns
- Hybrid scoring: Balanced measure of collaboration

\## 🛠 Technologies

- **Core**:
  - Java 23
  - Spring Boot 3.3.5
  - Gradle 8.x

- **Mathematical Analysis**:
  - JavaPlex (for TDA computations)
  - Apache Commons Math

- **Tools**:
  - MapStruct 1.5.5.Final (for object mapping)
  - Lombok (for boilerplate reduction)
  - JUnit 5 (for testing)

\## 🚀 Getting Started

\### Prerequisites
- JDK 23
- Gradle 8.x

\### Building the Project
```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

\### Configuration
Configure the service in `application.yml`:
```yaml
tda:
  analysis:
    threshold: 0.7
    bettiNumbers: 3
    maxDimension: 2
```

\## 📖 API Documentation

\### Base URL
```
http://localhost:8080/api/v1/tda
```

\### Endpoints
- POST /analyze - Analyze screenplay
- GET /metrics/{id} - Get analysis results
- GET /health - Service health check

\## 💻 Development

\### Layer Organization
- Domain Layer: Core business logic
- Application Layer: Use case implementations
- Infrastructure Layer: External integrations
- Presentation Layer: API endpoints

\## 🧪 Testing

\### Running Tests
```bash
# Run all tests
./gradlew test

# Run specific layer tests
./gradlew test --tests "com.cleanarchitecture.domain.*"
```

\### Test Categories
- Unit Tests: Framework-specific tests
- Integration Tests: Cross-framework interactions
- API Tests: Endpoint validation

\## 📚 References

- Farooq Khan et al. (2024) - AI Art Neural Constellation
- Kushnareva et al. (2021) - Artificial Text Detection via Topology
- Papia et al. (2023) - Entropy and Complexity Analysis