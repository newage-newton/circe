package me.prince.circe;

import me.prince.circe.dao.JokeDao;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JokeDaoTest extends BaseDaoTest {

    @Inject
    private JokeDao jokeDao;

    @Test
    void getJokeById() {
        assertNotNull(jokeDao.getJokeById(1));
    }

    @Test
    void insertJoke() {
        assertTrue(jokeDao.insertJoke("The word queue is ironic. It's just q with a bunch of silent letters waiting in line."));
        assertFalse(jokeDao.getAllJokes().isEmpty());
    }
}