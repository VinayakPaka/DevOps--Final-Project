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
Triggered automatically after a successful CI run. Uses a **self-hosted runner** with access to the Kubernetes cluster.
1.  **Update Deployment Manifest**: Injects DockerHub username and image tag.
2.  **Deploy to Kubernetes**: Applies K8s manifests using `kubectl`.
3.  **Verify Deployment**: Checks rollout status, pods, and services.
4.  **DAST**: Performs a dummy Dynamic Application Security Testing scan.

### Kubernetes Manifests (`k8s/`)
- `deployment.yaml`: Defines the Quiz App deployment with 2 replicas and health probes.
- `service.yaml`: Exposes the app via a LoadBalancer service.

## Setup & Configuration

### Self-Hosted Runner Setup
The CD pipeline requires a **self-hosted GitHub Actions runner** with `kubectl` configured to access your Kubernetes cluster.

1.  **Set up a Kubernetes Cluster**: Use Minikube, Docker Desktop Kubernetes, or a cloud provider (EKS, GKE, AKS).
2.  **Install GitHub Actions Runner**:
    - Go to your GitHub repo → Settings → Actions → Runners → New self-hosted runner.
    - Follow the instructions to install the runner on a machine with `kubectl` access.
3.  **Verify `kubectl` Access**: Ensure the runner can execute `kubectl get nodes` successfully.

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
