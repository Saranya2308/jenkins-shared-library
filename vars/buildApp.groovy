def call(String serviceName) {
    echo "Starting build process for ${serviceName}..."

    // Construct the build context path
    def buildContext = "${serviceName}/src"

    // Check if any recognized build file exists
    if (!fileExists("${buildContext}/package.json") &&
        !fileExists("${buildContext}/pom.xml") &&
        !fileExists("${buildContext}/build.gradle") &&
        !fileExists("${buildContext}/Dockerfile")) {
        error "❌ No recognized build file found in '${buildContext}'. Ensure you have a package.json, pom.xml, build.gradle, or Dockerfile."
    }

    // Go into the service's src folder for building
    dir(buildContext) {
        // Handle Node.js project
        if (fileExists("package.json")) {
            echo "📦 Detected Node.js project"
            if (isUnix()) {
                sh 'npm install'
                sh 'npm run build'
            } else {
                bat 'npm install'
                bat 'npm run build'
            }
        } 
        // Handle Maven project
        else if (fileExists("pom.xml")) {
            echo "☕ Detected Maven project"
            if (isUnix()) {
                sh 'mvn clean install'
            } else {
                bat 'mvn clean install'
            }
        } 
        // Handle Gradle project
        else if (fileExists("build.gradle")) {
            echo "🐘 Detected Gradle project"
            if (isUnix()) {
                sh './gradlew build'
            } else {
                bat 'gradlew.bat build'
            }
        } 
        // Handle Docker build
        else if (fileExists("Dockerfile")) {
            echo "🐳 Detected Dockerfile"
            echo "Building Docker image for ${serviceName}..."
            if (isUnix()) {
                sh "docker build -t ${serviceName}:${env.BUILD_ID} ."
            } else {
                bat "docker build -t ${serviceName}:${env.BUILD_ID} ."
            }
        } 
        // If none of the recognized files are found
        else {
            error "❌ No recognized build file found in '${buildContext}'. Ensure you have a package.json, pom.xml, build.gradle, or Dockerfile."
        }
    }
}
