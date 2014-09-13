package lerp.hotswap;

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.FileCollection
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.StopExecutionException

import lerp.hotswap.tasks.Spawn
import lerp.hotswap.extensions.HotswapExtension

class HotswapPlugin implements Plugin<Project> {
    static final String HOTSWAP_SPAWN_TASK_NAME = "JavaSpawn"

    @Override
    void apply(Project project) {
        if(!project.plugins.hasPlugin(JavaPlugin)) {
            throw new StopExecutionException("Project must apply java plugin")
        }

        project.extensions.create("hotswap", HotswapExtension, project)
        project.tasks.create(HOTSWAP_SPAWN_TASK_NAME, Spawn)
    }
}
