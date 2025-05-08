def call(Map config) {
    pipeline {
        agent any
        environment {
            IMAGE = "${config.imageName}:${env.BUILD_NUMBER}"
        }
        stages {
            stage('Checkout') {
                steps {
                    git url: config.repoUrl
                }
            }
            stage('Build Docker Image') {
                steps {
                    sh "docker build -t ${IMAGE} ."
                }
            }
            stage('Push Docker Image') {
                steps {
                    echo "Docker push stage - replace with registry logic if needed"
                    sh "docker tag ${IMAGE} myregistry/${IMAGE}"
                    sh "docker push myregistry/${IMAGE}"
                }
            }
        }
    }
}
