pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Source code checked out from GitHub'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Maven application...'
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'

                sh '''
                    DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                    docker build -t devops-status-app .
                '''
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'

                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                        echo "$DOCKER_PASS" | \
                        DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                        docker login -u "$DOCKER_USER" --password-stdin

                        DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                        docker tag devops-status-app:latest \
                        vardan3236/devops-status-app:latest

                        DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                        docker push vardan3236/devops-status-app:latest
                    '''
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying latest Docker image...'

                sh '''
                    DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                    docker rm -f devops-status-container || true

                    DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                    docker pull vardan3236/devops-status-app:latest

                    DOCKER_HOST=unix:///home/vardanreddy/.docker/desktop/docker.sock \
                    docker run --name devops-status-container \
                    vardan3236/devops-status-app:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'CI/CD Pipeline failed!'
        }
    }
}
