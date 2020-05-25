package me.prince.circe.writer;

import me.prince.circe.domain.StockPrice;
import me.prince.circe.serialization.AvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class StockPriceKafkaProducer implements StockPriceWriter {
    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Override
    public void writeStockPrice(StockPrice stockPrice) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());

        KafkaProducer<String, StockPrice> kafkaProducer = new KafkaProducer<>(properties);
        ProducerRecord<String, StockPrice> producerRecord = getProducerRecord(stockPrice);
        kafkaProducer.send(producerRecord,
                (recordMetadata, e) -> System.out.println(recordMetadata));
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private ProducerRecord<String, StockPrice> getProducerRecord(StockPrice stockPrice) {
        String key = String.join(":", stockPrice.getStockSymbol(), stockPrice.getDate().toString());
        return new ProducerRecord<>(kafkaTopic, key, stockPrice);
    }
}
