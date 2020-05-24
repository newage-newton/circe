package me.prince.circe.dao.mybatis;

import me.prince.circe.domain.StockPrice;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceMapper {
    int insertStockPrice(StockPrice stockPrice);

    StockPrice selectStockPrice(String stockSymbol, LocalDate date);

    List<StockPrice> selectAllStockPrice();

    int deleteStockPrice(String stockSymbol, LocalDate date);

}
