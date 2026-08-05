# 🚀 DevOps Status Application – Automated CI/CD Pipeline

A complete CI/CD project demonstrating how a Java Maven application can be automatically built, tested, containerized, pushed to Docker Hub, and deployed using Jenkins.

---

## 📌 Project Overview

This project demonstrates an automated CI/CD workflow using:

- Git & GitHub
- Java
- Maven
- Jenkins
- Docker
- Docker Hub
- GitHub Webhooks
- ngrok

Whenever code is pushed to the GitHub repository, a GitHub webhook automatically triggers the Jenkins pipeline.

Jenkins then builds the application, runs tests, creates a Docker image, pushes the image to Docker Hub, and deploys the latest version.

---

## 🏗️ CI/CD Architecture

Developer
   |
   | git push
   v
GitHub Repository
   |
   | GitHub Webhook
   v
ngrok
   |
   v
Jenkins
   |
   +--> Maven Build
   |
   +--> Maven Test
   |
   +--> Docker Build
   |
   +--> Docker Hub Push
   |
   +--> Docker Deployment
   |
   v
Application Running Successfully

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Application development |
| Maven | Build and dependency management |
| Git | Version control |
| GitHub | Source code repository |
| Jenkins | CI/CD automation |
| Docker | Application containerization |
| Docker Hub | Docker image registry |
| GitHub Webhook | Automatic Jenkins triggering |
| ngrok | Exposes local Jenkins to GitHub |

---

## 📁 Project Structure

```text
devops-status-app/
│
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
