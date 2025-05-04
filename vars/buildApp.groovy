def call() {
    echo "🔧 Starting build step..."

    // Check if package.json exists (Node.js)
    if (fileExists('package.json')) {
        echo "Node.js project detected"
        sh 'npm install'
        sh 'npm run build || true'  // Optional: Skips failure if build fails

    // Check if pom.xml exists (Java/Maven)
    } else if (fileExists('pom.xml')) {
        echo "Java/Maven project detected"
        sh 'mvn clean install -DskipTests'

    // Check if build.gradle exists (Java/Gradle)
    } else if (fileExists('build.gradle')) {
        echo "Java/Gradle project detected"
        sh './gradlew build'

    // Check if requirements.txt exists (Python)
    } else if (fileExists('requirements.txt')) {
        echo "Python project detected"
        sh 'pip install -r requirements.txt'

    // If no recognized project files, error out
    } else {
        error "❌ Could not detect project type for build"
    }

    echo "✅ Build step completed"
}
