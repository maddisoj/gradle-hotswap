package lerp.hotswap.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.GradleException

import com.sun.jdi.Bootstrap
import com.sun.jdi.VirtualMachine

import lerp.hotswap.util.HotswapHelper

class Deploy extends DefaultTask {
    private VirtualMachine vm

    Deploy() {
        vm = null
    }

    @TaskAction
    void deploy() {
        vm = HotswapHelper.connect(getPort())

        if(!vm.canRedefineClasses()) {
            throw new GradleException("JVM doesn't support class redefinition");
        }

        def now = Calendar.instance.timeInMillis

        println project.tasks.findByName('compileJava').dump()
    }

    int getPort() {
        return project.hotswap.port
    }
}
