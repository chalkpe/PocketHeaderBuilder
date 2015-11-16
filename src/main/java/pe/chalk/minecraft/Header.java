package pe.chalk.minecraft;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChalkPE <chalkpe@gmail.com>
 * @since 2015-11-16
 */
public class Header {
    private final String className;
    private final List<String> functions;

    public Header(String className, String... functions){
        this.className = className;
        this.functions = new ArrayList<>(Arrays.asList(functions));
    }

    public String getClassName(){
        return this.className;
    }

    public List<String> getFunctions(){
        return this.functions;
    }

    public Header addFunction(String function){
        this.getFunctions().add(function);
        return this;
    }

    public void save(){
        try{
            final Path path = Paths.get("out", this.getClassName() + ".h");
            if(Files.notExists(path.getParent())) Files.createDirectories(path.getParent());

            try(final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)){
                //TODO: Implement this stuff

                writer.write("public: ");
                writer.newLine();

                this.getFunctions().parallelStream().forEach(function -> {

                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
