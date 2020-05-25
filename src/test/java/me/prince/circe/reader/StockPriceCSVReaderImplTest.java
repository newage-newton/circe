package me.prince.circe.reader;

import me.prince.circe.BaseAppTest;
import me.prince.circe.domain.StockPrice;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class StockPriceCSVReaderTest extends BaseAppTest {

    @Inject
    StockPriceReader stockPriceReader;

    @Test
    public void readCSV() {
        List<StockPrice> stockPrices = stockPriceReader.read();
        assertFalse(stockPrices.isEmpty());
    }
}