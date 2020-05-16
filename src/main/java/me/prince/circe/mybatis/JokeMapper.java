package me.prince.circe.mybatis;

import java.util.List;

public interface JokeMapper {

    String getJokeById(Integer id);

    int insertJoke(String joke);

    List<String> getAllJokes();
}
