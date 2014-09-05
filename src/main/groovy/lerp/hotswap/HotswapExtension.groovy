package lerp.hotswap;

import org.gradle.api.file.FileCollection;

class HotswapExtension {
    private int port = 9000
    private String mainClass
    private FileCollection classpath

    void port(int port) {
        this.port = port;
    }

    int port() {
        return port;
    }

    void mainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    String mainClass() {
        return mainClass;
    }

    void classpath(FileCollection classpath) {
        this.classpath = classpath;
    }

    FileCollection classpath() {
        return classpath;
    }
}
