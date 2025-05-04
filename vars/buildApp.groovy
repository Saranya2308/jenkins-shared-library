def call() {
    echo "🔧 Starting build step..."

    def appDir = "shopping-app"

    if (fileExists("${appDir}/package.json")) {
        echo "📦 Detected Node.js project"
        dir(appDir) {
            sh 'npm install'
            sh 'npm run build'
        }
    } else if (fileExists("${appDir}/pom.xml")) {
        echo "☕ Detected Java Maven project"
        dir(appDir) {
            sh 'mvn clean install'
        }
    } else if (fileExists("${appDir}/build.gradle")) {
        echo "🎯 Detected Java Gradle project"
        dir(appDir) {
            sh './gradlew build'
        }
    } else if (fileExists("${appDir}/requirements.txt")) {
        echo "🐍 Detected Python project"
        dir(appDir) {
            sh 'pip install -r requirements.txt'
        }
    } else {
        error "❌ Could not detect project type for build in '${appDir}'"
    }
}
