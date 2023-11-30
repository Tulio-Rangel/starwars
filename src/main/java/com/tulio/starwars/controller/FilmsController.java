package com.tulio.starwars.controller;

import com.tulio.starwars.entity.ErrorResponse;
import com.tulio.starwars.entity.Film;
import com.tulio.starwars.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FilmsController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/films/{id}")
    public ResponseEntity<Object> getFilmById(@PathVariable String id) {
        // Validar si el ID es numérico y dentro de un rango definido
        if (!isValidId(id)) {
            ErrorResponse errorResponse = new ErrorResponse("error en la solicitud");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        int filmId = Integer.parseInt(id);
        Film film = filmService.getFilmById(filmId);
        if (film == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(film);
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Object> updateFilm(@PathVariable String id, @RequestBody Film updatedFilm) {
        // Validar si el ID es numérico y dentro de un rango definido
        if (!isValidId(id)) {
            ErrorResponse errorResponse = new ErrorResponse("error en la solicitud");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        int filmId = Integer.parseInt(id);
        Film updated = filmService.updateFilm(filmId, updatedFilm);

        if (updated == null) {
            ErrorResponse errorResponse = new ErrorResponse("Película no encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Object> deleteFilm(@PathVariable String id) {
        // Validar si el ID es numérico y dentro de un rango definido
        if (!isValidId(id)) {
            ErrorResponse errorResponse = new ErrorResponse("error en la solicitud");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        int filmId = Integer.parseInt(id);
        filmService.deleteFilmById(filmId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Método para validar el ID
    private boolean isValidId(String id) {
        try {
            int numericId = Integer.parseInt(id);
            return numericId > 0 && id.length() <= 2; // Validación de longitud arbitraria
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
