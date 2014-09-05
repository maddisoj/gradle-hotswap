package lerp.hotswap;

import lerp.hotswap.model.HotswapModel;

import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.JavaExec;

class JavaSpawn extends JavaExec {
    JavaSpawn() {
        description = "Spawns a new JVM running this project"
    }

    

    @Override
    String getMain() {
        return project.hotswap.mainClass
    }

    @Override
    FileCollection getClasspath() {
        return project.hotswap.classpath
    }
}
