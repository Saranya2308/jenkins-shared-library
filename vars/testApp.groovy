def call(String serviceName) {
    echo "🧪 Running tests for ${serviceName}"

    def servicePath = "${serviceName}/src"

    if (fileExists("${servicePath}/package.json")) {
        dir(servicePath) {
            sh 'npm install'
            sh 'npm test'
        }
    } else {
        echo "❌ No package.json found in ${servicePath}. Skipping tests."
    }
}
