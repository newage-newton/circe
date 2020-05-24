package me.prince.circe.domain;

import java.time.LocalDate;

public class StockPrice {
    private String stockSymbol;
    private LocalDate date;
    private Double price;

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
