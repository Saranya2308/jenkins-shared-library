def call() {
    echo "🐳 Building and pushing Docker image..."

    // Get Docker image tag based on build number or commit hash
    def tag = "${env.BUILD_NUMBER ?: 'latest'}"
    
    if (env.GIT_COMMIT) {
        tag = "${env.GIT_COMMIT.take(7)}" // Use short commit hash if available
    }

    // Set the image name
    def imageName = "my-docker-repo/my-app:${tag}"

    // Build the Docker image
    echo "Building Docker image: ${imageName}"
    sh "docker build -t ${imageName} ."

    // Push to Docker registry
    echo "Pushing Docker image: ${imageName}"
    sh "docker push ${imageName}"

    echo "✅ Docker build and push completed"
}
