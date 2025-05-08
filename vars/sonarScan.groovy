def call(Map config) {
    pipeline {
        agent any
        stages {
            stage('SonarQube Analysis') {
                steps {
                    withSonarQubeEnv('MySonarQubeServer') {
                        sh "sonar-scanner -Dsonar.projectKey=${config.projectKey} -Dsonar.sources=."
                    }
                }
            }
        }
    }
}
