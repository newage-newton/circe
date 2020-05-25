package me.prince.circe.serialization;

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {
    private final Class<T> targetClass;

    public AvroDeserializer(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        T result = null;
        if (bytes != null) {
            try {
                SpecificDatumReader<T> datumReader =
                        new SpecificDatumReader<>(targetClass.getDeclaredConstructor().newInstance().getSchema());
                BinaryDecoder binaryDecoder = DecoderFactory.get().binaryDecoder(bytes, null);
                result = datumReader.read(null, binaryDecoder);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
                throw new SerializationException("Can't deserialize data: " + Arrays.toString(bytes), e);
            }
        }
        return result;
    }
}
