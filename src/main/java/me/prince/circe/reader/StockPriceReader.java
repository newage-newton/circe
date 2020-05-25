package me.prince.circe.reader;

import me.prince.circe.domain.StockPrice;

import java.util.List;

public interface StockPriceReader {
    List<StockPrice> read();
}
