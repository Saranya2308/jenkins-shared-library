def call(String message) {
    echo "💬 Sending notification to Slack: ${message}"

    def slackWebhookUrl = "${env.SLACK_WEBHOOK_URL}" // Set in Jenkins credentials or environment variable

    if (!slackWebhookUrl) {
        error "Slack Webhook URL not found!"
    }

    def payload = [
        channel: '#your-channel', // Replace with your Slack channel
        text: message
    ]
    
    // Send message to Slack using curl
    sh """
        curl -X POST -H 'Content-type: application/json' --data '${groovy.json.JsonOutput.toJson(payload)}' ${slackWebhookUrl}
    """

    echo "✅ Slack notification sent"
}
