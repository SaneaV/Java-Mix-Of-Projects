package tut.baeldung.avro.serialization;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import tut.baeldung.avro.model.AvroHttpRequest;

import java.io.IOException;

public class AvroDeserializer {

    public AvroHttpRequest deserializeAvroHttpRequestJSON(byte[] data) {
        DatumReader<AvroHttpRequest> reader = new SpecificDatumReader<>(AvroHttpRequest.class);
        Decoder decoder;
        try {
            decoder = DecoderFactory.get()
                    .jsonDecoder(AvroHttpRequest.getClassSchema(), new String(data));
            return reader.read(null, decoder);
        } catch (IOException ignored) {
        }
        return null;
    }

    public AvroHttpRequest deserializeAvroHttpRequestBinary(byte[] data) {
        DatumReader<AvroHttpRequest> employeeReader = new SpecificDatumReader<>(AvroHttpRequest.class);
        Decoder decoder = DecoderFactory.get()
                .binaryDecoder(data, null);
        try {
            return employeeReader.read(null, decoder);
        } catch (IOException ignored) {
        }
        return null;
    }
}
