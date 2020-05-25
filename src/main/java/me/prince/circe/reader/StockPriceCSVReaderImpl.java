package me.prince.circe.reader;

import me.prince.circe.domain.StockPrice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockPriceCSVReaderImpl implements StockPriceReader {

    @Value("${feedsFilePath}")
    private String feedsFilePath;

    @Override
    public List<StockPrice> read() {
        List<StockPrice> results = new ArrayList<>();
        try {
            Reader csvReader = new FileReader(feedsFilePath);
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
            csvParser.forEach(record -> {
                StockPrice stockPrice = new StockPrice();
                stockPrice.setStockSymbol(record.get("stockSymbol"));
                stockPrice.setPrice(Double.parseDouble(record.get("price")));
                stockPrice.setDate(LocalDate.parse(record.get("date")));
                results.add(stockPrice);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}
