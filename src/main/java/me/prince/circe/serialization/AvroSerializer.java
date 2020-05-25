package me.prince.circe.serialization;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {

    @Override
    public byte[] serialize(String s, T t) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        SpecificDatumWriter<T> datumWriter = new SpecificDatumWriter<>(t.getSchema());
        try {
            datumWriter.write(t, binaryEncoder);
            binaryEncoder.flush();
            outputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Can't serialize data=" + t);
        }
    }
}
