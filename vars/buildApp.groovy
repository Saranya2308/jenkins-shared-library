def buildApp(appDir) {
    echo "Starting build process for ${appDir}..."
    
    // Check if package.json exists for Node.js
    if (fileExists("${appDir}/package.json")) {
        echo "📦 Detected Node.js project in '${appDir}'"
        dir("${appDir}") {
            // Install dependencies
            echo "Installing dependencies..."
            bat(script: 'npm install', returnStdout: true).eachLine { line -> echo line }
            // Run the build (change 'build' if you use another script in package.json)
            echo "Running build..."
            bat(script: 'npm run build', returnStdout: true).eachLine { line -> echo line }
        }
    }
    // Check if pom.xml exists for Maven (Java)
    else if (fileExists("${appDir}/pom.xml")) {
        echo "☕ Detected Java (Maven) project in '${appDir}'"
        dir("${appDir}") {
            // Run Maven clean install
            echo "Running Maven clean install..."
            bat(script: 'mvn clean install', returnStdout: true).eachLine { line -> echo line }
        }
    }
    // Check if build.gradle exists for Gradle (Java)
    else if (fileExists("${appDir}/build.gradle")) {
        echo "☕ Detected Java (Gradle) project in '${appDir}'"
        dir("${appDir}") {
            // Run Gradle build
            echo "Running Gradle build..."
            bat(script: 'gradle build', returnStdout: true).eachLine { line -> echo line }
        }
    }
    // Check if a Dockerfile exists
    else if (fileExists("${appDir}/Dockerfile")) {
        echo "🐳 Detected Docker project in '${appDir}'"
        dir("${appDir}") {
            // Build Docker image
            echo "Building Docker image..."
            bat(script: 'docker build -t my-app .', returnStdout: true).eachLine { line -> echo line }
        }
    }
    // If no recognized build file is found
    else {
        error "❌ No recognized build file found in '${appDir}'. Ensure you have a package.json, pom.xml, build.gradle, or Dockerfile."
    }
}
