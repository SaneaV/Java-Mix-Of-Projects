package tut.baeldung.avro;

import tut.baeldung.avro.model.AvroHttpRequest;
import tut.baeldung.avro.model.ClientIdentifier;
import tut.baeldung.avro.serialization.AvroDeserializer;
import tut.baeldung.avro.serialization.AvroSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tut.baeldung.avro.model.Active.YES;

public class App {

    private static final AvroSerializer serializer = new AvroSerializer();
    private static final AvroDeserializer deserializer = new AvroDeserializer();

    public static void main(String[] args) {

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostName("localhost")
                .setIpAddress("255.255.255.0")
                .build();

        List<CharSequence> employees = new ArrayList<>();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        AvroHttpRequest request = AvroHttpRequest.newBuilder()
                .setRequestTime(1L)
                .setActive(YES)
                .setClientIdentifier(clientIdentifier)
                .setEmployeeNames(employees)
                .build();

        byte[] bytes = serializer.serializeAvroHttpRequestJSON(request);
        System.out.println("\nSerialize JSON: " + Arrays.toString(bytes));
        AvroHttpRequest avroHttpRequest = deserializer.deserializeAvroHttpRequestJSON(bytes);
        System.out.println("\nDeserialize JSON: " + avroHttpRequest);

        bytes = serializer.serializeAvroHttpRequestBinary(request);
        System.out.println("\nSerialize Binary: " + Arrays.toString(bytes));
        avroHttpRequest = deserializer.deserializeAvroHttpRequestBinary(bytes);
        System.out.println("\nDeserialize Binary: " + avroHttpRequest);
    }
}
