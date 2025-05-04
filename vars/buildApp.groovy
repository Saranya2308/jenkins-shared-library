def buildApp(appDir) {
    // Check if package.json exists for Node.js
    if (fileExists("${appDir}/package.json")) {
        echo "📦 Detected Node.js project"
        dir("${appDir}") {
            // Install dependencies
            bat 'npm install'
            // Run the build (change 'build' if you use another script in package.json)
            bat 'npm run build'
        }
    }
    // Check if pom.xml exists for Maven (Java)
    else if (fileExists("${appDir}/pom.xml")) {
        echo "☕ Detected Java (Maven) project"
        dir("${appDir}") {
            // Run Maven clean install
            bat 'mvn clean install'
        }
    }
    // Check if build.gradle exists for Gradle (Java)
    else if (fileExists("${appDir}/build.gradle")) {
        echo "☕ Detected Java (Gradle) project"
        dir("${appDir}") {
            // Run Gradle build
            bat 'gradle build'
        }
    }
    // Check if a Dockerfile exists
    else if (fileExists("${appDir}/Dockerfile")) {
        echo "🐳 Detected Docker project"
        dir("${appDir}") {
            // Build Docker image
            bat 'docker build -t my-app .'
        }
    }
    // If no recognized build file is found
    else {
        error "❌ No recognized build file found in '${appDir}'."
    }
}
