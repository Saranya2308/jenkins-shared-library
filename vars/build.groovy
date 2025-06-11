import org.mycompany.Utils

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
            stage('Docker Login') {
                steps {
                    script {
                        try {
                            Utils.dockerLogin(this, config.registry, config.dockerCredentialsId)
                        } catch (err) {
                            error("Docker login failed: ${err}")
                        }
                    }
                }
            }
            stage('Build Docker Image') {
                steps {
                    script {
                        try {
                            sh "docker build -t ${IMAGE} ."
                        } catch (err) {
                            error("Docker build failed: ${err}")
                        }
                    }
                }
            }
            stage('Push Docker Image') {
                steps {
                    script {
                        try {
                            sh "docker push ${IMAGE}"
                        } catch (err) {
                            error("Docker push failed: ${err}")
                        }
                    }
                }
            }
        }
    }
}
