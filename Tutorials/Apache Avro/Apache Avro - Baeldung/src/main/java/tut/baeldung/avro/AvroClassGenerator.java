package tut.baeldung.avro;

import org.apache.avro.Schema;
import org.apache.avro.compiler.specific.SpecificCompiler;

import java.io.File;
import java.io.IOException;

public class AvroClassGenerator {

    public static void main(String[] args) {
        try {
            generateAvroClasses();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generateAvroClasses() throws IOException {
        SpecificCompiler compiler = new SpecificCompiler(new Schema.Parser().parse(new File("src/main/resources/avroHttpRequest-schema.avsc")));
        compiler.compileToDestination(new File("src/main/resources"), new File("src/main/java"));
    }
}
