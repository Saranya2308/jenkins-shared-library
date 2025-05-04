def call(String environment) {
    echo "🚀 Deploying to ${environment}..."

    def imageTag = "${env.BUILD_NUMBER ?: 'latest'}"
    
    // Adjust this section based on your deployment tool
    if (environment == 'staging') {
        echo "Deploying to Staging using Helm"
        // Example: Helm deployment
        sh """
            helm upgrade --install my-app-staging ./helm/my-app \
            --set image.tag=${imageTag} \
            --namespace staging
        """
    } else if (environment == 'production') {
        echo "Deploying to Production using Helm"
        // Example: Helm deployment to production
        sh """
            helm upgrade --install my-app-prod ./helm/my-app \
            --set image.tag=${imageTag} \
            --namespace production
        """
    } else {
        error "Unknown environment: ${environment}"
    }

    echo "✅ Deployment to ${environment} completed"
}
