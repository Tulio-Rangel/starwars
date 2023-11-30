package com.tulio.starwars.controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tulio.starwars.entity.Film;
import com.tulio.starwars.service.FilmService;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FilmsController.class})
@ExtendWith(SpringExtension.class)
class FilmsControllerTest {
    @MockBean
    private FilmService filmService;

    @Autowired
    private FilmsController filmsController;

    @Test
    void testDeleteFilm() throws Exception {
        doNothing().when(filmService).deleteFilmById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/films/{id}", "42");
        MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetFilmById() throws Exception {
        Film film = new Film();
        film.setEpisodeId(1);
        film.setReleaseDate(LocalDate.of(1970, 1, 1));
        film.setTitle("Dr");
        when(filmService.getFilmById(anyInt())).thenReturn(film);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/films/{id}", "42");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(String.join("", "{\"episodeId\":1,\"title\":\"Dr\",\"",
                System.getProperty("jdk.debug"), "Date\":[1970,1,1]}")));
    }

    @Test
    void testGetFilmById2() throws Exception {
        Film film = new Film();
        film.setEpisodeId(1);
        film.setReleaseDate(LocalDate.of(1970, 1, 1));
        film.setTitle("Dr");
        when(filmService.getFilmById(anyInt())).thenReturn(film);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/films/{id}", "Uri Variables",
                "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"error en la solicitud\"}"));
    }

    @Test
    void testUpdateFilm() throws Exception {
        Film film = new Film();
        film.setEpisodeId(1);
        film.setReleaseDate(LocalDate.of(1970, 1, 1));
        film.setTitle("Dr");
        when(filmService.updateFilm(anyInt(), Mockito.<Film>any())).thenReturn(film);

        Film film2 = new Film();
        film2.setEpisodeId(1);
        film2.setReleaseDate(null);
        film2.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(film2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/films/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(String.join("", "{\"episodeId\":1,\"title\":\"Dr\",\"",
                System.getProperty("jdk.debug"), "Date\":[1970,1,1]}")));
    }

    @Test
    void testUpdateFilm2() throws Exception {
        Film film = new Film();
        film.setEpisodeId(1);
        film.setReleaseDate(LocalDate.of(1970, 1, 1));
        film.setTitle("Dr");
        when(filmService.updateFilm(anyInt(), Mockito.<Film>any())).thenReturn(film);

        Film film2 = new Film();
        film2.setEpisodeId(1);
        film2.setReleaseDate(null);
        film2.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(film2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/films/{id}", "Uri Variables", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"error en la solicitud\"}"));
    }

    @Test
    void testDeleteFilm2() throws Exception {
        doNothing().when(filmService).deleteFilmById(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/films/{id}", "Uri Variables",
                "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(filmsController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"error en la solicitud\"}"));
    }
}
