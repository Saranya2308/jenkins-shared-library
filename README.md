A Jenkins Shared Library to automate CI/CD across 20+ microservices, reducing deployement time by 40%
This Jenkins Shared Library provides reusable, modular pipeline steps and helper classes to streamline Continuous Integration and Continuous Deployment (CI/CD) workflows for Dockerized applications with Kubernetes deployment and SonarQube integration.

The library abstracts common pipeline logic such as:

- Git checkout
- Docker image build, tag, push with secure credential handling
- Application testing
- SonarQube static code analysis
- Kubernetes deployment

Using this library enables standardized, maintainable, and DRY (Don't Repeat Yourself) Jenkins pipelines across multiple projects.
