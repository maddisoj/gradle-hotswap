package lerp.hotswap;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.FileCollection;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.StopExecutionException;

class HotswapPlugin implements Plugin<Project> {
    private Project project
    private HotswapExtension extension

    void apply(Project project) {
        if(!project.plugins.hasPlugin(JavaPlugin)) {
            throw new StopExecutionException("Project must apply java plugin");
        }

        this.project   = project
        this.extension = project.extensions.create("hotswap", HotswapExtension)

        project.tasks.create("JavaSpawn", JavaSpawn)
    }
}
