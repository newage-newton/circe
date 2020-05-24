package me.prince.circe.dao.mybatis;

import java.util.List;

public interface JokeMapper {

    String getJokeById(Integer id);

    int insertJoke(String joke);

    List<String> getAllJokes();
}
