package lerp.hotswap.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.incremental.IncrementalTaskInputs

import com.sun.jdi.Bootstrap
import com.sun.jdi.VirtualMachine

import lerp.hotswap.util.HotswapHelper

class Deploy extends DefaultTask {
    @InputDirectory
    File inputDir

    private VirtualMachine vm

    Deploy() {
        vm = null
        inputDir = project.buildDir
    }

    @TaskAction
    void deploy(IncrementalTaskInputs inputs) {
        inputs.outOfDate { changed ->
            println changed.file.name
        }
    }

    int getPort() {
        return project.hotswap.port
    }
}
