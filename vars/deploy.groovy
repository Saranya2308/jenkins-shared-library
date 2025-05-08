def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('Deploy to Kubernetes') {
                steps {
                    sh "kubectl apply -f k8s/${config.serviceName}.yaml"
                }
            }
        }
    }
}
