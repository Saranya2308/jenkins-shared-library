package org.mycompany

class Utils {
    static String getVersion(String baseVersion, String buildNum) {
        return "${baseVersion}-${buildNum}"
    }
}
