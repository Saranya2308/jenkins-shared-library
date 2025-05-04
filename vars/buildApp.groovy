def call(String serviceName) {
    echo "Starting build process for ${serviceName}..."

    // Construct the build context path
    def buildContext = "${serviceName}/src"

    if (!fileExists("${buildContext}/package.json") &&
        !fileExists("${buildContext}/pom.xml") &&
        !fileExists("${buildContext}/build.gradle") &&
        !fileExists("${buildContext}/Dockerfile")) {
        error "❌ No recognized build file found in '${buildContext}'. Ensure you have a package.json, pom.xml, build.gradle, or Dockerfile."
    }

    dir(buildContext) {
        if (fileExists("package.json")) {
            echo "📦 Detected Node.js project"
            if (isUnix()) {
                sh 'npm install'
                sh 'npm run build'
            } else {
                bat 'npm install'
                bat 'npm run build'
            }
        } else if (fileExists("pom.xml")) {
            echo "☕ Detected Maven project"
            if (isUnix()) {
                sh 'mvn clean install'
            } else {
                bat 'mvn clean install'
            }
        } else if (fileExists("build.gradle")) {
            echo "🐘 Detected Gradle project"
            if (isUnix()) {
                sh './gradlew build'
            } else {
                bat 'gradlew.bat build'
            }
        } else if (fileExists("Dockerfile")) {
            echo "🐳 No app build file found, but Dockerfile is present. Will build Docker image later."
        }
    }
}
