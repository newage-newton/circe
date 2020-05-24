package me.prince.circe.dao;

import java.util.List;

public interface JokeDao {
    String getJokeById(Integer id);

    Boolean insertJoke(String joke);

    List<String> getAllJokes();
}
