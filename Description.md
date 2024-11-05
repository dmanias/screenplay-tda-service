# Screenplay TDA Analysis Service

## Description
A Spring Boot microservice that performs Topological Data Analysis (TDA) on screenplays to evaluate narrative structure, creative patterns, and quality metrics. Part of a larger AI-assisted screenplay generation system.

## Key Features
- Feature extraction from screenplay text using advanced TDA algorithms
- Persistence diagram computation for structural analysis
- Quality metrics calculation based on topological features
- Pattern analysis for human/AI content differentiation
- RESTful API for screenplay analysis integration

## Technical Stack
- Java 23
- Spring Boot 3.2.x
- Spring Data JPA
- JavaPlex (for TDA computations)
- Lombok
- MongoDB (document storage)
- Redis (caching)

## Getting Started

### Prerequisites
- JDK 23 or higher
- Maven 3.6.x or higher
- MongoDB instance
- Redis instance

### Installation
1. Clone the repository
```bash
git clone https://github.com/yourusername/screenplay-tda-service.git
cd screenplay-tda-service
```

2. Configure application properties
```bash
cp src/main/resources/application.example.yml src/main/resources/application.yml
# Edit application.yml with your configuration
```

3. Build the project
```bash
mvn clean install
```

4. Run the service
```bash
mvn spring-boot:run
```

### API Documentation
The service exposes RESTful endpoints for screenplay analysis:

```
POST /api/v1/analysis/screenplay
GET /api/v1/analysis/{id}
GET /api/v1/analysis/{id}/metrics
GET /api/v1/analysis/{id}/persistence-diagram
```

Detailed API documentation is available at `/swagger-ui.html` when running the service.

## Architecture

### Core Components
- Feature Extraction Service
- Persistence Diagram Generator
- Topological Analysis Engine
- Quality Metrics Calculator

### Theoretical Framework
Based on research from:
- Kushnareva et al. (2021)
- Farooq Khan et al. (2024)
- Fukui et al. (2021)

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Citation
If you use this service in your research, please cite:

```bibtex
@software{screenplay_tda_service,
  title={Screenplay TDA Analysis Service},
  year={2024},
  author={Your Name},
  url={https://github.com/yourusername/screenplay-tda-service}
}
```

## Contact
Your Name - your.email@example.com
Project Link: https://github.com/yourusername/screenplay-tda-service