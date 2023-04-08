package tut.baeldung.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

import static java.util.Collections.emptyList;

public class AvroSchemaBuilder {
    public Schema createAvroHttpRequestSchema() {

        final Schema clientIdentifier = SchemaBuilder
                .record("ClientIdentifier")
                .namespace("tut.baeldung.avro.model")
                .fields()
                .requiredString("hostName")
                .requiredString("ipAddress")
                .endRecord();

        final Schema employeeName = SchemaBuilder.builder("tut.baeldung.avro.model").array().items().stringType();
        final Schema active = SchemaBuilder.enumeration("Active").namespace("tut.baeldung.avro.model").symbols("YES", "NO");

        return SchemaBuilder
                .record("AvroHttpRequest")
                .namespace("tut.baeldung.avro.model")
                .fields()
                .requiredLong("requestTime")
                .name("clientIdentifier").type(clientIdentifier).noDefault()
                .name("employeeNames").type(employeeName).withDefault(emptyList())
                .name("active").type(active).noDefault()
                .endRecord();
    }
}
