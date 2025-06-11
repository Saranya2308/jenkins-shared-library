package org.mycompany

class Utils implements Serializable {
    static void dockerLogin(script, String registry, String credentialsId) {
        script.withCredentials([script.usernamePassword(credentialsId: credentialsId, usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            script.sh "echo \$PASSWORD | docker login ${registry} --username \$USERNAME --password-stdin"
        }
    }

    // ...other utility methods...
}
