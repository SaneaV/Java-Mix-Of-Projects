package tut.baeldung.avro.serialization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tut.baeldung.avro.model.AvroHttpRequest;
import tut.baeldung.avro.model.ClientIdentifier;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static tut.baeldung.avro.model.Active.YES;

public class AvroSerializerDeSerializerIntegrationTest {

    AvroSerializer serializer;
    AvroDeserializer deserializer;
    AvroHttpRequest request;

    @BeforeEach
    public void setUp() {
        serializer = new AvroSerializer();
        deserializer = new AvroDeserializer();

        ClientIdentifier clientIdentifier = ClientIdentifier.newBuilder()
                .setHostName("localhost")
                .setIpAddress("255.255.255.0")
                .build();

        List<CharSequence> employees = new ArrayList<>();
        employees.add("James");
        employees.add("Alice");
        employees.add("David");
        employees.add("Han");

        request = AvroHttpRequest.newBuilder()
                .setRequestTime(1L)
                .setActive(YES)
                .setClientIdentifier(clientIdentifier)
                .setEmployeeNames(employees)
                .build();
    }

    @Test
    public void WhenSerializedUsingJSONEncoder_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        assertThat(data).isNotNull();
        assertThat(data.length).isGreaterThan(0);
    }

    @Test
    public void WhenSerializedUsingBinaryEncoder_thenObjectGetsSerialized() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        assertThat(data).isNotNull();
        assertThat(data.length).isGreaterThan(0);
    }

    @Test
    public void WhenDeserializeUsingJSONDecoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestJSON(request);
        AvroHttpRequest actualRequest = deserializer.deserializeAvroHttpRequestJSON(data);
        assertThat(actualRequest).isEqualTo(request);
        assertThat(actualRequest.getRequestTime()).isEqualTo(request.getRequestTime());
    }

    @Test
    public void WhenDeserializeUsingBinaryDecoder_thenActualAndExpectedObjectsAreEqual() {
        byte[] data = serializer.serializeAvroHttpRequestBinary(request);
        AvroHttpRequest actualRequest = deserializer.deserializeAvroHttpRequestBinary(data);
        assertThat(actualRequest).isEqualTo(request);
        assertThat(actualRequest.getRequestTime()).isEqualTo(request.getRequestTime());
    }
}

