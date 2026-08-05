# 🚀 DevOps Status Application – Automated CI/CD Pipeline

A hands-on DevOps project that demonstrates an automated CI/CD pipeline for a Java Maven application using Jenkins and Docker.

Whenever code is pushed to GitHub, a GitHub webhook triggers Jenkins automatically. Jenkins builds and tests the application, creates a Docker image, pushes it to Docker Hub, and deploys the latest image.

---

## 📌 Project Overview

The goal of this project is to automate the software delivery workflow from source-code changes to container deployment.

The pipeline implements:

```text
Developer
   ↓
Git Push
   ↓
GitHub Repository
   ↓
GitHub Webhook
   ↓
ngrok
   ↓
Jenkins
   ↓
Maven Build
   ↓
Maven Test
   ↓
Docker Build
   ↓
Docker Hub Push
   ↓
Docker Deployment
```

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java 17 | Application development/runtime |
| Maven | Build, packaging, and testing |
| Git | Version control |
| GitHub | Source-code repository |
| Jenkins | CI/CD pipeline automation |
| Docker | Containerization and deployment |
| Docker Hub | Container image registry |
| GitHub Webhooks | Automatic Jenkins pipeline trigger |
| ngrok | Exposes local Jenkins to GitHub |

---

## 📁 Project Structure

```text
devops-status-app/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

## ⚙️ CI/CD Pipeline Stages

### 1. Checkout

Jenkins retrieves the latest source code from the GitHub repository.

### 2. Build

The Java application is compiled and packaged with Maven:

```bash
mvn clean package
```

The generated JAR file is placed in the `target/` directory.

### 3. Test

Jenkins runs the automated Maven tests:

```bash
mvn test
```

The tested pipeline successfully completed with:

```text
Tests run: 1
Failures: 0
Errors: 0
Skipped: 0
```

### 4. Docker Build

After the Maven stages succeed, Jenkins creates a Docker image:

```bash
docker build -t devops-status-app .
```

The Dockerfile packages the generated JAR into a Java 17 runtime image.

### 5. Docker Hub Push

Jenkins authenticates to Docker Hub using credentials stored securely in Jenkins Credentials.

The local image is tagged as:

```bash
docker tag devops-status-app:latest vardan3236/devops-status-app:latest
```

Then it is pushed to Docker Hub:

```bash
docker push vardan3236/devops-status-app:latest
```

Jenkins credential ID:

```text
dockerhub-credentials
```

The Docker Hub password/access token is not hard-coded in the Jenkinsfile.

### 6. Deploy

Jenkins removes the previous container if it exists:

```bash
docker rm -f devops-status-container
```

It then pulls the latest image:

```bash
docker pull vardan3236/devops-status-app:latest
```

Finally, Jenkins runs the latest container image:

```bash
docker run --name devops-status-container vardan3236/devops-status-app:latest
```

The current application is a console-style Java application. It prints its status and exits successfully rather than running continuously as a web server.

---

## 🔄 GitHub Webhook Automation

The Jenkins job is configured with:

```text
GitHub hook trigger for GITScm polling
```

Because Jenkins is running locally on port `8081`, ngrok is used to provide a temporary public HTTPS endpoint:

```bash
ngrok http 8081
```

The GitHub webhook payload URL follows this format:

```text
https://<ngrok-domain>/github-webhook/
```

The webhook is configured for push events.

After configuration, a GitHub push successfully triggered Jenkins automatically:

```text
Started by GitHub push
```

> Note: The ngrok tunnel must remain running for GitHub to reach a Jenkins instance hosted only on the local machine. A new ngrok URL may require updating the GitHub webhook.

---

## 🔐 Credentials Management

Docker Hub credentials are stored in Jenkins instead of being committed to GitHub.

Credential configuration:

```text
Type: Username with password
Credential ID: dockerhub-credentials
```

The Jenkins pipeline accesses the credential using `withCredentials`.

Never commit passwords, access tokens, or other secrets to the repository.

---

## 🐳 Docker Image

Docker Hub image:

```text
vardan3236/devops-status-app:latest
```

Pull the image:

```bash
docker pull vardan3236/devops-status-app:latest
```

Run the image:

```bash
docker run --name devops-status-container vardan3236/devops-status-app:latest
```

---

## 🧪 Application Output

A successful deployment produces:

```text
================================
     DevOps Status Application
================================
Application : DevOps Status Application
Version     : 1.0
Environment : Development
Status      : Running Successfully
================================
```

---

## ✅ Pipeline Result

The complete automated workflow has been tested successfully:

| Stage | Result |
|---|---|
| GitHub Webhook Trigger | ✅ Success |
| Source Checkout | ✅ Success |
| Maven Build | ✅ Success |
| Maven Tests | ✅ Success |
| Docker Build | ✅ Success |
| Docker Hub Authentication | ✅ Success |
| Docker Hub Push | ✅ Success |
| Docker Deployment | ✅ Success |

Final Jenkins result:

```text
CI/CD Pipeline completed successfully!
Finished: SUCCESS
```

---

## 🚀 How to Run the Project

### Prerequisites

Install/configure:

- Git
- Java 17
- Maven
- Jenkins
- Docker
- ngrok

### Clone the repository

```bash
git clone https://github.com/vardanreddy256/devops-status-app.git
cd devops-status-app
```

### Build locally

```bash
mvn clean package
```

### Run tests

```bash
mvn test
```

### Build the Docker image

```bash
docker build -t devops-status-app .
```

### Run the Docker image

```bash
docker run --name devops-status-container devops-status-app
```

---

## 🔁 Testing the Automatic Pipeline

Start Jenkins and ensure Docker is running.

If Jenkins is local, start ngrok:

```bash
ngrok http 8081
```

Make a code change and push it:

```bash
git add .
git commit -m "Update application"
git push origin main
```

Do not manually click **Build Now** when testing the webhook.

A successful setup automatically triggers Jenkins after the push.

---

## 🧠 What I Learned

This project provided hands-on experience with:

- Git and GitHub workflows
- Maven build automation
- Automated Java testing
- Jenkins Declarative Pipelines
- Writing and maintaining a `Jenkinsfile`
- Docker image creation
- Docker container management
- Docker Hub image publishing
- Jenkins Credentials management
- GitHub Webhook integration
- ngrok tunneling for local Jenkins
- Automated deployment
- Troubleshooting CI/CD pipeline failures

---

## 🛠️ Troubleshooting Covered During the Project

Some practical issues solved while building this project included:

- Jenkins Docker context configuration
- Connecting Jenkins to the Docker Desktop socket
- Docker container-name conflicts
- Jenkinsfile brace/stage placement errors
- Docker Hub authentication through Jenkins
- GitHub webhook `404` troubleshooting
- Keeping the ngrok tunnel available for webhook delivery
- Verifying automatic builds using `Started by GitHub push`

---

## 📈 Future Improvements

Possible enhancements include:

- Convert the console application into a Spring Boot web application
- Deploy containers to Kubernetes
- Deploy the application to AWS
- Add SonarQube static code analysis
- Add Trivy container vulnerability scanning
- Add Prometheus monitoring
- Add Grafana dashboards
- Add Slack or email notifications
- Use versioned Docker tags instead of only `latest`
- Host Jenkins on a server/cloud VM instead of exposing localhost through ngrok

---

## 👨‍💻 Author

**Yasovardhan Reddy**

DevOps & Cloud Enthusiast

GitHub username: `vardanreddy256`

Docker Hub username: `vardan3236`

---

## 📊 Project Status

**Completed ✅**

The project successfully demonstrates an automated CI/CD workflow integrating GitHub, Jenkins, Maven, Docker, Docker Hub, GitHub Webhooks, ngrok, and automated container deployment.
