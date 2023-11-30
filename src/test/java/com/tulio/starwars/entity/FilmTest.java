package com.tulio.starwars.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FilmTest {
    @Test
    void testConstructor() {
        Film actualFilm = new Film();
        actualFilm.setEpisodeId(1);
        LocalDate releaseDate = LocalDate.of(1970, 1, 1);
        actualFilm.setReleaseDate(releaseDate);
        actualFilm.setTitle("Dr");
        int actualEpisodeId = actualFilm.getEpisodeId();
        LocalDate actualReleaseDate = actualFilm.getReleaseDate();
        assertEquals("Dr", actualFilm.getTitle());
        assertEquals(1, actualEpisodeId);
        assertSame(releaseDate, actualReleaseDate);
    }

    @Test
    void testConstructor2() {
        Film actualFilm = new Film(1, "Dr", LocalDate.of(1970, 1, 1));
        actualFilm.setEpisodeId(1);
        LocalDate releaseDate = LocalDate.of(1970, 1, 1);
        actualFilm.setReleaseDate(releaseDate);
        actualFilm.setTitle("Dr");
        int actualEpisodeId = actualFilm.getEpisodeId();
        LocalDate actualReleaseDate = actualFilm.getReleaseDate();
        assertEquals("Dr", actualFilm.getTitle());
        assertEquals(1, actualEpisodeId);
        assertSame(releaseDate, actualReleaseDate);
    }
}
