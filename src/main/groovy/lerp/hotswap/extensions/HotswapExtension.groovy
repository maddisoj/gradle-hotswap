package lerp.hotswap.extensions

import org.gradle.api.Project
import org.gradle.util.ConfigureUtil

class HotswapExtension {
    SpawnExtension spawn

    HotswapExtension(Project project) {
        spawn = new SpawnExtension(project)
    }

    void spawn(Closure closure) {
        ConfigureUtil.configure(closure, spawn)
    }
}
