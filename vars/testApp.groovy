def call(String serviceName) {
    echo "🧪 Running tests for service: ${serviceName}"

    // Define the path to check for package.json
    def servicePath = "${serviceName}\\src" // Windows path uses backslashes

    // Check if package.json exists
    if (fileExists("${servicePath}\\package.json")) {
        echo "📦 package.json found. Installing dependencies and running tests..."

        dir(servicePath) {
            // Use bat for Windows shell commands
            bat 'npm install'
            bat 'npm test'
        }
    } else {
        echo "❌ No package.json found in ${servicePath}. Skipping tests for ${serviceName}."
    }
}
