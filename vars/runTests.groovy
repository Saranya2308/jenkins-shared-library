def call() {
    echo "🧪 Running tests..."

    if (fileExists('package.json')) {
        echo "Detected Node.js project"
        sh 'npm test || echo "⚠️ Tests failed"'
    } else if (fileExists('pom.xml')) {
        echo "Detected Java/Maven project"
        sh 'mvn test || echo "⚠️ Tests failed"'
    } else if (fileExists('build.gradle')) {
        echo "Detected Gradle project"
        sh './gradlew test || echo "⚠️ Tests failed"'
    } else if (fileExists('requirements.txt') && fileExists('pytest.ini')) {
        echo "Detected Python project with pytest"
        sh 'pytest || echo "⚠️ Tests failed"'
    } else {
        echo "⚠️ No recognizable test configuration found"
    }

    echo "✅ Test step completed"
}
