package lerp.hotswap.util

import org.gradle.api.Project
import org.gradle.api.GradleException

import com.sun.jdi.Bootstrap
import com.sun.jdi.VirtualMachine

class HotswapHelper {
    private static final String CONNECTOR_SOCKET_NAME = "dt_socket"

    private HotswapHelper() {}

    static VirtualMachine connect(int port) {
        def manager = Bootstrap.virtualMachineManager(),
            connectors = manager.attachingConnectors(),
            connector = connectors.find {
                it.transport().name() == CONNECTOR_SOCKET_NAME
            }

        if(connector == null) {
            throw new GradleException("Unable to find suitable connector")
        } else {
            def args = connector.defaultArguments()

            try {
                args.port.setValue(port)

                return connector.attach(args)
            } catch(ConnectException e) {
                throw new GradleException("Could not connect to JVM: $e.message")
            } catch(NullPointerException e) {
                throw new GradleException("Could not set port argument.")
            }
        }
    }

    static void printCapabilities(VirtualMachine vm) {
        println "Redefine Classes: " + vm.canRedefineClasses()
        println "Add Methods: " + vm.canAddMethod()
    }

    static byte[] loadClassFiles(File classFile) {
        return classFile.bytes
    }
}
