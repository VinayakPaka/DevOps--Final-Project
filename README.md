# DevOps Final Project: CI/CD for Java Quiz App

## Project Overview
This project demonstrates a production-grade CI/CD pipeline for a simple Java Spring Boot Quiz Application. The pipeline, implemented using GitHub Actions, automates the build, testing, quality assurance, security scanning, containerization, and deployment processes.

## Application Description
The application is a RESTful API built with Spring Boot that allows fetching general knowledge questions and submitting answers.
- **Language**: Java 17
- **Framework**: Spring Boot 3
- **Build Tool**: Maven
- **Containerization**: Docker

## CI/CD Architecture
The pipeline is designed with "Shift-Left" security principles, ensuring code quality and security are checked early in the process.

### CI Pipeline (`.github/workflows/ci.yml`)
1.  **Build and Test**: Compiles the code and runs unit tests to prevent regressions.
2.  **Linting**: Uses Checkstyle to enforce coding standards and prevent technical debt.
3.  **Security Scan**:
    *   **SAST**: Uses Semgrep to check for code-level vulnerabilities.
    *   **SCA**: Uses Trivy to scan dependencies for known vulnerabilities.
4.  **Docker Build & Push**:
    *   Builds the Docker image.
    *   Scans the image using Trivy (Container Security).
    *   Performs a smoke test (Runtime Validation) by starting the container and hitting the API.
    *   Pushes the trusted image to Docker Hub.

### CD Pipeline (`.github/workflows/cd.yml`)
Triggered automatically after a successful CI run.
1.  **Deployment**: Simulates deployment to a Kubernetes cluster.
2.  **DAST**: Performs a dummy Dynamic Application Security Testing scan.

## Setup & configuration

### Prerequisites
- Docker installed locally.
- Java 17 installed locally (optional, if using Docker).
- GitHub Account with a repository.
- Docker Hub Account.

### Secrets Configuration
You **MUST** configure the following secrets in your GitHub Repository settings:
- `DOCKERHUB_USERNAME`: Your Docker Hub username.
- `DOCKERHUB_TOKEN`: Access token for Docker Hub.

### How to Run Locally

#### Using Maven
```bash
mvn spring-boot:run
```
Access the API: `curl http://localhost:8080/api/quiz/questions`

#### Using Docker
```bash
docker build -t quiz-app .
docker run -p 8080:8080 quiz-app
```

## Insights vs. Justification
- **Linting** is separate to ensure code style doesn't block critical builds but is still enforced.
- **Security Scans** are split into SAST (code) and SCA (dependencies) to cover different attack vectors.
- **Runtime Validation** in CI provides a high confidence level that the image being pushed actually works.
