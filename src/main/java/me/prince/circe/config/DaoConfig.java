package me.prince.circe.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.prince.circe.dao.mybatis.JokeMapper;
import me.prince.circe.dao.mybatis.StockPriceMapper;
import me.prince.circe.domain.StockPrice;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
@ComponentScan(basePackages = "me.prince.circe.dao")
public class DaoConfig {

    @Bean
    public DataSource hikariDatasource() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/circe");
        config.addDataSourceProperty("databaseName", "circe");
        return new HikariDataSource(config);
    }

    @Bean
    public TransactionManager transactionManager() {
        return new DataSourceTransactionManager(hikariDatasource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        Environment environment = new Environment("development", new SpringManagedTransactionFactory(), hikariDatasource());
        Configuration configuration = new Configuration(environment);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMapper(JokeMapper.class);
        configuration.addMapper(StockPriceMapper.class);
        configuration.getTypeAliasRegistry().registerAlias("JokeMapper", JokeMapper.class);
        configuration.getTypeAliasRegistry().registerAlias("StockPriceMapper", StockPriceMapper.class);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        return sqlSessionFactoryBuilder.build(configuration);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
