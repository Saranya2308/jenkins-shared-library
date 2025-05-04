def call(String serviceName) {
    echo "🐳 Building and pushing Docker image for ${serviceName}..."

    // Set the build context to the 'src' directory of the service
    def buildContext = "${serviceName}/src"

    // Get Docker image tag based on build number or commit hash
    def tag = "${env.BUILD_NUMBER ?: 'latest'}"
    
    if (env.GIT_COMMIT) {
        tag = "${env.GIT_COMMIT.take(7)}" // Use short commit hash if available
    }

    // Set the image name
    def imageName = "my-docker-repo/${serviceName}:${tag}"

    // Build the Docker image
    echo "Building Docker image: ${imageName} from ${buildContext}"
    if (isUnix()) {
        sh "docker build -t ${imageName} -f ${buildContext}/Dockerfile ${buildContext}"
    } else {
        bat "docker build -t ${imageName} -f ${buildContext}/Dockerfile ${buildContext}"
    }

    // Push to Docker registry
    echo "Pushing Docker image: ${imageName}"
    if (isUnix()) {
        sh "docker push ${imageName}"
    } else {
        bat "docker push ${imageName}"
    }

    echo "✅ Docker build and push completed for ${serviceName}"
}
