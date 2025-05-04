def call(String appDir) {
    echo "Starting build process for ${appDir}..."
    
    if (fileExists("${appDir}/package.json")) {
        echo "📦 Detected Node.js project in '${appDir}'"
        dir("${appDir}") {
            echo "Installing dependencies..."
            bat(script: 'npm install', returnStdout: true).eachLine { line -> echo line }
            echo "Running build..."
            bat(script: 'npm run build', returnStdout: true).eachLine { line -> echo line }
        }
    } else if (fileExists("${appDir}/pom.xml")) {
        echo "☕ Detected Java (Maven) project in '${appDir}'"
        dir("${appDir}") {
            echo "Running Maven clean install..."
            bat(script: 'mvn clean install', returnStdout: true).eachLine { line -> echo line }
        }
    } else if (fileExists("${appDir}/build.gradle")) {
        echo "☕ Detected Java (Gradle) project in '${appDir}'"
        dir("${appDir}") {
            echo "Running Gradle build..."
            bat(script: 'gradle build', returnStdout: true).eachLine { line -> echo line }
        }
    } else if (fileExists("${appDir}/Dockerfile")) {
        echo "🐳 Detected Docker project in '${appDir}'"
        dir("${appDir}") {
            echo "Building Docker image..."
            bat(script: 'docker build -t my-app .', returnStdout: true).eachLine { line -> echo line }
        }
    } else {
        error "❌ No recognized build file found in '${appDir}'. Ensure you have a package.json, pom.xml, build.gradle, or Dockerfile."
    }
}
