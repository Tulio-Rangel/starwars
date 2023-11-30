package com.tulio.starwars.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tulio.starwars.entity.Film;
import com.tulio.starwars.entity.FilmApi;
import com.tulio.starwars.repository.FilmRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {FilmService.class})
@ExtendWith(SpringExtension.class)
class FilmServiceTest {
    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private FilmService filmService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetFilmById() throws RestClientException {
        ResponseEntity<FilmApi> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getStatusCode()).thenReturn(null);
        when(restTemplate.getForEntity(Mockito.<String>any(), Mockito.<Class<FilmApi>>any(), isA(Object[].class)))
                .thenReturn(responseEntity);
        Film actualFilmById = filmService.getFilmById(1);
        verify(responseEntity).getStatusCode();
        verify(restTemplate).getForEntity(Mockito.<String>any(), Mockito.<Class<FilmApi>>any(), isA(Object[].class));
        assertNull(actualFilmById);
    }

    @Test
    void testUpdateFilm() {
        Film film = new Film();
        film.setEpisodeId(1);
        film.setReleaseDate(LocalDate.of(1970, 1, 1));
        film.setTitle("Dr");
        Optional<Film> ofResult = Optional.of(film);

        Film film2 = new Film();
        film2.setEpisodeId(1);
        film2.setReleaseDate(LocalDate.of(1970, 1, 1));
        film2.setTitle("Dr");
        when(filmRepository.save(Mockito.<Film>any())).thenReturn(film2);
        when(filmRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Film updatedFilm = new Film();
        updatedFilm.setEpisodeId(1);
        updatedFilm.setReleaseDate(LocalDate.of(1970, 1, 1));
        updatedFilm.setTitle("Dr");
        Film actualUpdateFilmResult = filmService.updateFilm(1, updatedFilm);
        verify(filmRepository).findById(Mockito.<Integer>any());
        verify(filmRepository).save(Mockito.<Film>any());
        assertSame(film2, actualUpdateFilmResult);
    }

    @Test
    void testUpdateFilm2() {
        Optional<Film> emptyResult = Optional.empty();
        when(filmRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Film updatedFilm = new Film();
        updatedFilm.setEpisodeId(1);
        updatedFilm.setReleaseDate(LocalDate.of(1970, 1, 1));
        updatedFilm.setTitle("Dr");
        Film actualUpdateFilmResult = filmService.updateFilm(1, updatedFilm);
        verify(filmRepository).findById(Mockito.<Integer>any());
        assertNull(actualUpdateFilmResult);
    }

    @Test
    void testDeleteFilmById() {
        doNothing().when(filmRepository).deleteById(Mockito.<Integer>any());
        filmService.deleteFilmById(1);
        verify(filmRepository).deleteById(Mockito.<Integer>any());
    }
}
