\# Clean Architecture Template

A Spring Boot project template following Clean Architecture principles.

\## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

\## 🎯 Overview

This project is a template for building Spring Boot applications following Clean Architecture principles, focusing on separation of concerns and maintainability.

\### Key Features
- Clean Architecture implementation
- Domain-Driven Design (DDD) principles
- API documentation with OpenAPI (Swagger)
- Comprehensive test coverage
- Gradle build system
- Java 23 support

\## 🏗 Architecture

The architecture follows Clean Architecture principles with dependencies pointing inward:

```
┌──────────────────────────────────────┐
│ Presentation Layer (Interface)       │
│  ┌──────────────────────────────┐   │
│  │ Infrastructure Layer         │   │
│  │  ┌──────────────────────┐   │   │
│  │  │ Application Layer    │   │   │
│  │  │  ┌──────────────┐   │   │   │
│  │  │  │ Domain Layer │   │   │   │
│  │  │  └──────────────┘   │   │   │
│  │  └──────────────────────┘   │   │
│  └──────────────────────────────┘   │
└──────────────────────────────────────┘
```

\### 1. Domain Layer (Enterprise Business Rules)
- Location: `src/main/java/com/cleanarchitecture/domain`
- Purpose: Contains core business logic and rules
- Contains:
   - Business entities
   - Value objects
   - Domain events
   - Domain exceptions
   - Port interfaces (for dependency inversion)
      - Input ports (use case interfaces)
      - Output ports (repository/service interfaces)
   - Domain exceptions

\### 2. Application Layer (Application Business Rules)
- Location: `src/main/java/com/cleanarchitecture/application`
- Purpose: Orchestrates the flow of data and business rules
- Contains:
   - Use case implementations
   - Application services
   - DTOs for internal use

\### 3. Infrastructure Layer (Frameworks & Drivers)
- Location: `src/main/java/com/cleanarchitecture/infrastructure`
- Contains:
   - Repository implementations
   - External service integrations
   - Security configurations
   - Database configurations

\### 4. Presentation Layer (Interface Adapters)
- Location: `src/main/java/com/cleanarchitecture/presentation`
- Contains:
   - REST controllers
   - Request/Response models
   - API documentation
   - Exception handlers

\## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── cleanarchitecture/
│   │           ├── domain/
│   │           │   ├── entity/        # Business entities
│   │           │   ├── valueobject/   # Immutable value objects
│   │           │   ├── event/         # Domain events
│   │           │   ├── port/          # Interfaces for external deps
│   │           │   │   ├── input/     # Use case interfaces
│   │           │   │   └── output/    # Repository/Service interfaces
│   │           │   └── exception/     # Domain-specific exceptions
│   │           ├── application/
│   │           │   ├── service/       # Use case implementations
│   │           │   └── dto/           # Data Transfer Objects
│   │           ├── infrastructure/
│   │           │   ├── persistence/   # Repository implementations
│   │           │   ├── config/        # Framework configurations
│   │           │   └── security/      # Security configurations
│   │           └── presentation/
│   │               └── api/
│   │                   └── rest/
│   │                       ├── controller/  # REST controllers
│   │                       ├── request/     # Request DTOs
│   │                       └── response/    # Response DTOs
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-prod.yml
└── test/
    └── java/
        └── com/
            └── cleanarchitecture/
                ├── domain/
                ├── application/
                ├── infrastructure/
                └── presentation/
```

\## 🛠 Technologies

- **Core**:
   - Java 23
   - Spring Boot 3.3.5
   - Gradle 8.x

- **Documentation**:
   - SpringDoc OpenAPI UI 2.3.0
   - Swagger UI

- **Persistence**:
   - Spring Data JPA
   - H2 Database (for development)

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
# Clone the repository
git clone https://github.com/yourusername/clean-architecture-template.git

# Navigate to the project directory
cd clean-architecture-template

# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

\### Configuration
The application can be configured using the following files:
- `application.yml`: Default configuration
- `application-dev.yml`: Development configuration
- `application-prod.yml`: Production configuration

\## 📖 API Documentation

OpenAPI documentation is available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- API Docs: http://localhost:8080/api-docs

\## 💻 Development

\### Code Style
This project uses:
- EditorConfig for consistent coding style
- Google Java Format
- Checkstyle for code quality enforcement

\### Mapper Generation
MapStruct is used for object mapping. Mappers are generated during compilation:
```bash
./gradlew clean build
```

\### Dependencies Flow
The dependencies flow inward, following DIP:
```
Controller → Domain Port ← Infrastructure Implementation
     ↓          ↓                 ↓
 Request → Application Service → Entity
     ↓          ↓                 ↑
Response ← DTO   ← Domain Port Interface
```

\### Working with Layers

\#### 1. Domain Layer
- Create entities in `domain/entity`
- Define value objects in `domain/valueobject`
- Define domain events in `domain/event`
- Define interfaces in `domain/port`

\#### 2. Application Layer
- Implement use cases in `application/service`
- Define DTOs in `application/dto`

\#### 3. Infrastructure Layer
- Implement repositories in `infrastructure/persistence`
- Add configurations in `infrastructure/config`

\#### 4. Presentation Layer
- Add controllers in `presentation/api/rest/controller`
- Define request/response models in respective packages

\## 🧪 Testing

\### Running Tests
```bash
# Run all tests
./gradlew test

# Run specific test category
./gradlew test --tests "com.cleanarchitecture.domain.*"
```

\### Test Categories
- Unit Tests: `src/test/java/.../domain/`
- Integration Tests: `src/test/java/.../infrastructure/`
- API Tests: `src/test/java/.../presentation/`

\## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

\## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details