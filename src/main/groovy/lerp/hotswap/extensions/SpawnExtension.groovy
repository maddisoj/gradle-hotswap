package lerp.hotswap.extensions

import org.gradle.api.Project
import org.gradle.api.file.FileCollection

class SpawnExtension {
    String mainClass
    FileCollection classpath

    SpawnExtension(Project project) {
        classpath = project.sourceSets.main.runtimeClasspath
    }

    void mainClass(String mainClass) {
        this.mainClass = mainClass
    }

    void classpath(FileCollection classpath) {
        this.classpath = classpath
    }
}
