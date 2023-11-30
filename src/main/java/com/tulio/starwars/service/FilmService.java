package com.tulio.starwars.service;

import com.tulio.starwars.entity.Film;
import com.tulio.starwars.entity.FilmApi;
import com.tulio.starwars.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class FilmService {
    private static final String SWAPI_FILMS_URL = "https://swapi.dev/api/films/";

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Film getFilmById(int id) {
        String filmUrl = SWAPI_FILMS_URL + id + "/";

        try {
            ResponseEntity<FilmApi> response = restTemplate.getForEntity(filmUrl, FilmApi.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                FilmApi filmFromApi = response.getBody();

                // Mapear los campos desde la respuesta del API a la entidad Film
                Film filmToSave = new Film();
                assert filmFromApi != null;
                filmToSave.setEpisodeId(filmFromApi.getEpisodeId());
                filmToSave.setTitle(filmFromApi.getTitle());
                filmToSave.setReleaseDate(LocalDate.parse(filmFromApi.getReleaseDate()));

                // Guardar la película en la base de datos
                filmRepository.save(filmToSave);

                return filmToSave;
            }

        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        } catch (HttpClientErrorException.BadRequest ex) {
            return null;
        }
        return null;
    }

    public Film updateFilm(int id, Film updatedFilm) {
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if (optionalFilm.isPresent()) {
            Film filmToUpdate = optionalFilm.get();

            // Actualizar los campos de la película
            filmToUpdate.setTitle(updatedFilm.getTitle());
            filmToUpdate.setReleaseDate(updatedFilm.getReleaseDate());

            // Guardar la película actualizada en la base de datos
            return filmRepository.save(filmToUpdate);
        } else {
            return null;
        }
    }
    public void deleteFilmById(int id) {
        filmRepository.deleteById(id);
    }
}
