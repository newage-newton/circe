package me.prince.circe.writer;

import me.prince.circe.BaseAppTest;
import me.prince.circe.domain.StockPrice;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;

class StockPriceKafkaProducerTest extends BaseAppTest {
    @Inject
    private StockPriceKafkaProducer stockPriceKafkaProducer;

    @Test
    public void testProducer() {
        StockPrice stockPrice = new StockPrice("AAPL", 1.0D, LocalDate.of(2020, 5, 25));
        stockPriceKafkaProducer.writeStockPrice(stockPrice);
    }
}