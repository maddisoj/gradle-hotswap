package lerp.hotswap.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskAction

class Spawn extends DefaultTask {
    Spawn () {
        description = "Spawns a JVM on the user defined port ready for hot " +
                      "code replacement."
    }

    @TaskAction
    void spawn() {
        def cmd = [ command, jvmArgs, mainClass ].flatten()*.toString(),
            builder = new ProcessBuilder(cmd)

        builder.with {
            //inheritIO()
            directory(project.projectDir)
            start()
        }
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

    String getCommand() {
        return "java";
    }

    List getJvmArgs() {
        return [
            "-Xdebug",
            "-Xrunjdwp:transport=dt_socket,address=$port,server=y,suspend=n",
            "-cp",
            classpath.files.join(File.pathSeparator)
        ]
    }
}
