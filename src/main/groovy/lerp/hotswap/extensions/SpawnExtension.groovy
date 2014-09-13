package lerp.hotswap.extensions

import org.gradle.api.Project

class SpawnExtension {
    String mainClass

    SpawnExtension(Project project) {
        mainClass = null
    }

    void mainClass(String mainClass) {
        this.mainClass = mainClass
    }
}
