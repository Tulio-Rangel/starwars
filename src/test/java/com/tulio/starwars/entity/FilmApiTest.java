package com.tulio.starwars.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FilmApiTest {
    @Test
    void testConstructor() {
        FilmApi actualFilmApi = new FilmApi();
        actualFilmApi.setEpisodeId(1);
        actualFilmApi.setReleaseDate("2020-03-01");
        actualFilmApi.setTitle("Dr");
        int actualEpisodeId = actualFilmApi.getEpisodeId();
        String actualReleaseDate = actualFilmApi.getReleaseDate();
        assertEquals("2020-03-01", actualReleaseDate);
        assertEquals("Dr", actualFilmApi.getTitle());
        assertEquals(1, actualEpisodeId);
    }

    @Test
    void testConstructor2() {
        FilmApi actualFilmApi = new FilmApi("Dr", 1, "2020-03-01");
        actualFilmApi.setEpisodeId(1);
        actualFilmApi.setReleaseDate("2020-03-01");
        actualFilmApi.setTitle("Dr");
        int actualEpisodeId = actualFilmApi.getEpisodeId();
        String actualReleaseDate = actualFilmApi.getReleaseDate();
        assertEquals("2020-03-01", actualReleaseDate);
        assertEquals("Dr", actualFilmApi.getTitle());
        assertEquals(1, actualEpisodeId);
    }
}
