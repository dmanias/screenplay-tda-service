# TDA Analysis Service

A Spring Boot microservice implementing Topological Data Analysis for screenplay evaluation using Clean Architecture principles.

## 🎯 Overview

This service implements TDA (Topological Data Analysis) for screenplay evaluation using three core frameworks:
- Modal Logic Framework for creative analysis
- Topological Analysis Framework for feature extraction
- Pattern Analysis Framework for human-AI pattern differentiation

## 🏗 Architecture

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

### 1. Domain Layer (Enterprise Business Rules)
- Location: `src/main/java/com/cleanarchitecture/domain`
- Purpose: Contains core business logic and rules
- Components:
```
domain/
├── entity/          # Business entities
│   ├── Screenplay.java
│   ├── Scene.java
│   └── Dialogue.java
├── valueobject/     # Immutable value objects
│   ├── TopologicalFeatures.java
│   ├── PersistenceDiagram.java
│   ├── PatternMetrics.java
│   └── CreativeMetrics.java
├── validator/       # Domain validation
│   ├── BaseValidator.java
│   └── ScreenplayDomainValidator.java
├── port/           # Interface definitions
│   ├── input/      # Use case interfaces
│   │   ├── ModalLogicPort.java
│   │   ├── TopologicalAnalysisPort.java
│   │   └── PatternAnalysisPort.java
│   └── output/     # External dependency interfaces
│       └── FeatureExtractionPort.java
└── exception/      # Domain-specific exceptions
    └── DomainException.java
```

### 2. Application Layer (Application Business Rules)
- Purpose: Orchestrates the flow of data and business rules
- Components:
```
application/
├── service/        # Use case implementations
│   ├── ModalLogicService.java
│   ├── TopologicalAnalysisService.java
│   └── PatternAnalysisService.java
└── dto/           # Data Transfer Objects
    └── AnalysisResult.java
```

### 3. Infrastructure Layer (Frameworks & Drivers)
- Purpose: Implements interfaces defined in domain ports
- Components:
```
infrastructure/
└── persistence/
    ├── entity/              # JPA entities (data models)
    │   ├── ScreenplayJpaEntity.java
    │   ├── SceneJpaEntity.java
    │   └── DialogueJpaEntity.java
    ├── mapper/              # Mappers between JPA entities and domain entities
    │   ├── ScreenplayMapper.java
    │   ├── SceneMapper.java
    │   └── DialogueMapper.java
    ├── repository/          # Spring Data JPA repositories and implementations
    │   ├── jpa/            # Spring Data JPA repository interfaces
    │   │   └── ScreenplayJpaRepository.java
    │   └── impl/           # Implementation of domain repository interfaces
    │       └── ScreenplayRepositoryImpl.java
```

### 4. Presentation Layer (Interface Adapters)
- Purpose: Handles HTTP requests/responses
- Components:
```
presentation/
└── api/
    └── rest/
        ├── controller/  # REST controllers
        │   └── TDAController.java
        ├── request/     # Request DTOs
        │   └── ScreenplayRequest.java
        └── response/    # Response DTOs
            └── AnalysisResponse.java
```

## 🧮 Theoretical Foundation

### Modal Logic Framework
- Creative Potential: ◇(A ∧ C)
- Human Creativity Foundation: □(H → ◇C)
- Collaborative Creation: ◇(A ∧ H ∧ C)

### Topological Analysis Framework
- Feature Extraction: F: Text → R^n
- Persistence Diagram: PH(text) = {(b_i, d_i)}
- Quality Metrics: Q(text) = (β₀, β₁, β₂, W)

### Pattern Analysis Framework
- Human patterns: More varied topological structures
- AI patterns: More regular and predictable patterns
- Hybrid scoring: Balanced measure of collaboration

## Dependencies Flow

The dependencies flow inward:
```
Controller → Domain Port ← Infrastructure Implementation
     ↓          ↓                 ↓
 Request → Application Service → Entity
     ↓          ↓                 ↑
Response ← DTO   ← Domain Port Interface
```

## Mathematical Foundation Integration

The clean architecture allows us to separate:
1. Core TDA concepts (domain layer)
2. Implementation of algorithms (infrastructure layer)
3. Coordination of analysis (application layer)
4. Presentation of results (presentation layer)

This separation ensures that mathematical foundations remain pure and unaffected by technical concerns.