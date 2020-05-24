package me.prince.circe.dao;

import me.prince.circe.domain.StockPrice;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StockPriceDaoImpl extends BaseDao implements StockPriceDao {
    @Inject
    public StockPriceDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
    }

    @Override
    public void insertStockPrice(StockPrice stockPrice) {
        getSqlSession().insert("insertStockPrice", stockPrice);
    }

    @Override
    public StockPrice selectStockPrice(String stockSymbol, LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("stockSymbol", stockSymbol);
        params.put("date", date);
        return getSqlSession().selectOne("selectStockPrice", params);
    }

    @Override
    public List<StockPrice> selectAllStockPrice() {
        return getSqlSession().selectList("selectAllStockPrice");
    }

    @Override
    public void deleteStockPrice(String stockSymbol, LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("stockSymbol", stockSymbol);
        params.put("date", date);
        getSqlSession().delete("deleteStockPrice", params);
    }
}
