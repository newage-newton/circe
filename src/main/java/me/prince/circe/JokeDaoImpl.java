package me.prince.circe;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class JokeDaoImpl extends BaseDao implements JokeDao {
    @Inject
    private SqlSessionTemplate sqlSessionTemplate;

    JokeDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
    }

    @Override
    public String getJokeById(Integer id) {
        return getSqlSession().selectOne("getJokeById", id);
    }

    @Override
    public Boolean insertJoke(String joke) {
        return getSqlSession().insert("insertJoke", joke) == 1;
    }

    @Override
    public List<String> getAllJokes() {
        return getSqlSession().selectList("getAllJokes");
    }
}
