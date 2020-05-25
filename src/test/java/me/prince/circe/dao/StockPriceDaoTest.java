package me.prince.circe.dao;

import me.prince.circe.domain.StockPrice;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockPriceDaoTest extends BaseDaoTest {
    @Inject
    private StockPriceDao stockPriceDao;

    @Test
    public void testStockPriceDao() {
        StockPrice s1 = new StockPrice();
        s1.setDate(LocalDate.of(2020, 5, 17));
        s1.setPrice(1.0D);
        s1.setStockSymbol("AAPL");

        StockPrice s2 = new StockPrice();
        s2.setDate(LocalDate.of(2020, 5, 19));
        s2.setPrice(2.0D);
        s2.setStockSymbol("WORK");

        stockPriceDao.insertStockPrice(s1);
        assertEquals(1.0D, stockPriceDao.selectStockPrice("AAPL", LocalDate.of(2020, 5, 17)).getPrice());

        stockPriceDao.insertStockPrice(s2);
        assertEquals(2, stockPriceDao.selectAllStockPrice().size());

        stockPriceDao.deleteStockPrice("WORK", LocalDate.of(2020, 5, 19));
        assertEquals(1, stockPriceDao.selectAllStockPrice().size());

        stockPriceDao.deleteStockPrice("AAPL", LocalDate.of(2020, 5, 17));
        assertEquals(0, stockPriceDao.selectAllStockPrice().size());
    }
}
