def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('Deploy to Kubernetes') {
                steps {
                    // Use 'bat' for running commands on Windows instead of 'sh'
                    bat "kubectl apply -f k8s/${config.serviceName}.yaml"
                }
            }
        }
    }
}
