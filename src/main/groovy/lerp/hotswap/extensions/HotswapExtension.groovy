package lerp.hotswap.extensions

import org.gradle.api.Project
import org.gradle.util.ConfigureUtil
import org.gradle.api.file.FileCollection

class HotswapExtension {
    int port
    FileCollection classpath

    SpawnExtension spawn
    DeployExtension deploy

    HotswapExtension(Project project) {
        port = 9000
        classpath = project.sourceSets.main.runtimeClasspath
        spawn = new SpawnExtension(project)
        deploy = new DeployExtension(project)
    }

    void spawn(Closure closure) {
        ConfigureUtil.configure(closure, spawn)
    }

    void deploy(Closure closure) {
        ConfigureUtil.configure(closure, deploy)
    }
}
