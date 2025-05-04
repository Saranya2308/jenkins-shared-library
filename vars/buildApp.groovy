def call() {
    echo "🔧 Starting build step..."

    if (fileExists('package.json')) {
        echo "Node.js project detected"
        sh 'npm install'
        sh 'npm run build || true' // optional
    } else if (fileExists('pom.xml')) {
        echo "Java/Maven project detected"
        sh 'mvn clean install -DskipTests'
    } else if (fileExists('build.gradle')) {
        echo "Java/Gradle project detected"
        sh './gradlew build'
    } else if (fileExists('requirements.txt')) {
        echo "Python project detected"
        sh 'pip install -r requirements.txt'
    } else {
        error "❌ Could not detect project type for build"
    }

    echo "✅ Build step completed"
}
