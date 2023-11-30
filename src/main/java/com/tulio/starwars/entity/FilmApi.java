package com.tulio.starwars.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmApi {
    private String title;

    @JsonProperty("episode_id")
    private int episodeId;

    @JsonProperty("release_date")
    private String releaseDate;

}