package lerp.hotswap.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.file.BaseDirFileResolver
import org.gradle.api.internal.file.FileResolver
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.StopExecutionException
import org.gradle.process.internal.ExecHandle
import org.gradle.process.internal.ExecHandleState
import org.gradle.process.internal.AbstractExecHandleBuilder
import org.gradle.process.internal.JavaExecHandleBuilder
import org.gradle.util.CollectionUtils

import javax.inject.Inject

class Spawn extends DefaultTask {
    protected AbstractExecHandleBuilder execHandleBuilder

    Spawn () {
        description = "Spawns a persitent JVM on the set port ready for " +
                      "hot code replacement."
    }

    @TaskAction
    void spawn() {
        AbstractExecHandleBuilder execHandleBuilder = getExecHandleBuilder()
        ExecHandle execHandle = execHandleBuilder?.build()

        execHandle?.start()?.waitForFinish()
    }

    protected AbstractExecHandleBuilder getExecHandleBuilder() {
        AbstractExecHandleBuilder execHandleBuilder =
            new JavaExecHandleBuilder(this.fileResolver)

        execHandleBuilder.main = this.mainClass
        execHandleBuilder.classpath = this.classpath
        execHandleBuilder.jvmArgs = this.jvmArgs
        execHandleBuilder.daemon  = true

        return execHandleBuilder
    }

    @Inject
    FileResolver getFileResolver() {
        throw new UnsupportedOperationException()
    }

    String getMainClass() {
        return project.hotswap.spawn.mainClass
    }

    FileCollection getClasspath() {
        return project.hotswap.classpath
    }

    int getPort() {
        return project.hotswap.port
    }

    Iterable<String> getJvmArgs() {
        return [
            "-Xdebug",
            "-Xrunjdwp:transport=dt_socket,address=" + this.port 
            + ",server=y,suspend=n"
        ]
    }
}
