\# Clean Architecture Layers - TDA Service

\## Overview

Clean Architecture organizes code into concentric circles representing different layers of the application, where:
- Inner circles contain business logic (domain)
- Outer circles contain implementation details
- Dependencies can only point inwards
- Inner circles don't know about outer circles

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

\## 1. Domain Layer (Enterprise Business Rules)

\### Purpose
- Contains the core business logic and rules
- Independent of all external concerns
- Represents the heart of the application
- Defines contracts for external dependencies (ports)

\### Components
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
├── port/           # Interface definitions
│   ├── input/      # Use case interfaces
│   │   ├── ModalLogicPort.java
│   │   ├── TopologicalAnalysisPort.java
│   │   └── PatternAnalysisPort.java
│   └── output/     # External dependency interfaces
│       └── FeatureExtractionPort.java
└── exception/      # Domain-specific exceptions
    └── TDAAnalysisException.java
```

\### Key Characteristics
- No dependencies on frameworks or external libraries
- Pure Java code
- Contains business logic validation
- Houses business invariants
- Defines all interfaces for external operations

\### Example
```java
// Domain Entity
public class Screenplay {
    private final ScreenplayId id;
    private List<Scene> scenes;
    private ScreenplayMetrics metrics;

    public void analyze() {
        if (scenes.isEmpty()) {
            throw new InvalidScreenplayException("Screenplay must have at least one scene");
        }
        // Domain logic for analysis
    }
}

// Domain Port (Input)
public interface TopologicalAnalysisPort {
    TopologicalFeatures extractFeatures(Screenplay screenplay);
    PersistenceDiagram computePersistenceDiagram(Screenplay screenplay);
}

// Domain Port (Output)
public interface FeatureExtractionPort {
    List<Double> extractGradientFeatures(Screenplay screenplay);
    List<Double> extractConnectivityPatterns(Screenplay screenplay);
}
```

\## 2. Application Layer (Application Business Rules)

\### Purpose
- Orchestrates the flow of data and business rules
- Implements use cases defined in domain ports
- Coordinates domain objects

\### Components
```
application/
├── service/        # Use case implementations
│   ├── ModalLogicService.java
│   ├── TopologicalAnalysisService.java
│   └── PatternAnalysisService.java
└── dto/           # Data Transfer Objects
    └── AnalysisResult.java
```

\### Key Characteristics
- Depends only on the domain layer
- Implements interfaces defined in domain ports
- Contains no business rules
- Coordinates domain objects

\### Example
```java
@Service
public class TopologicalAnalysisService implements TopologicalAnalysisPort {
    private final FeatureExtractionPort featureExtractor;

    @Override
    public TopologicalFeatures extractFeatures(Screenplay screenplay) {
        List<Double> gradientFeatures = featureExtractor.extractGradientFeatures(screenplay);
        List<Double> connectivityPatterns = featureExtractor.extractConnectivityPatterns(screenplay);

        return TopologicalFeatures.builder()
                .gradientFeatures(gradientFeatures)
                .connectivityPatterns(connectivityPatterns)
                .build();
    }
}
```

\## 3. Infrastructure Layer (Frameworks & Drivers)

\### Purpose
- Implements interfaces defined in domain ports
- Contains all external dependencies
- Handles technical concerns

\### Components
```
infrastructure/
├── persistence/    # Implementation of domain ports
│   └── JavaPlexFeatureExtraction.java
└── config/        # Framework configurations
    └── TDAConfig.java
```

\### Key Characteristics
- Contains framework-specific code
- Implements data persistence
- Handles external service communication
- Configures technical aspects

\### Example
```java
@Service
public class JavaPlexFeatureExtraction implements FeatureExtractionPort {
    private final SimplexStreamBuilder simplexBuilder;

    @Override
    public List<Double> extractGradientFeatures(Screenplay screenplay) {
        // JavaPlex-specific implementation
        return computeFeatures(screenplay);
    }

    private List<Double> computeFeatures(Screenplay screenplay) {
        // Implementation using JavaPlex library
    }
}
```

\## 4. Presentation Layer (Interface Adapters)

\### Purpose
- Handles HTTP requests/responses
- Converts data between formats
- Provides API endpoints
- Manages user interface concerns

\### Components
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

\### Key Characteristics
- Contains controllers and presenters
- Handles HTTP-specific logic
- Manages request/response formats
- Implements API documentation

\### Example
```java
@RestController
@RequestMapping("/api/v1/tda")
public class TDAController {
    private final TopologicalAnalysisPort topologicalAnalysisPort;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResponse> analyzeScreenplay(
            @Valid @RequestBody ScreenplayRequest request) {
        Screenplay screenplay = screenplayMapper.toDomain(request);
        TopologicalFeatures features = topologicalAnalysisPort.extractFeatures(screenplay);
        return ResponseEntity.ok(AnalysisResponse.from(features));
    }
}
```

\## Dependencies Flow

The dependencies flow inward, following the Dependency Inversion Principle:

```
Controller → Domain Port ← Infrastructure Implementation
     ↓          ↓                 ↓
 Request → Application Service → Entity
     ↓          ↓                 ↑
Response ← DTO   ← Domain Port Interface
```

\## Benefits

1. **Independence of Mathematical Framework**
  - Core analysis logic isn't tied to JavaPlex
  - Easy to change mathematical computation tools

2. **Testability**
  - Business rules can be tested without frameworks
  - Easy to mock TDA computations

3. **Independence of Analysis Tools**
  - Analysis logic can change without affecting business rules
  - Multiple tools can implement same interfaces

4. **Maintainability**
  - Clear separation of concerns
  - Easy to locate and modify code
  - Protected from external changes

\## Mathematical Foundation Integration

The clean architecture allows us to separate:
1. Core TDA concepts (domain layer)
2. Implementation of algorithms (infrastructure layer)
3. Coordination of analysis (application layer)
4. Presentation of results (presentation layer)

This separation ensures that mathematical foundations remain pure and unaffected by technical concerns.