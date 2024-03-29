package net.palacesoft.tmdb.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


class Movie {

    public final static MOVIE_URL = "http://www.themoviedb.org/movie"

    boolean adult
    String original_title
    String tagline
    String overview
    String homepage
    String poster_path
    double popularity
    int revenue
    String status
    int id
    String title
    float vote_average
    int vote_count
    String imdb_id
    float runtime
    int budget
    String backdrop_path
    @JsonIgnore String belongs_to_collection
    List<Genre> genres
    List<Company> production_companies
    List<Country> production_countries
    List<Language> spoken_languages
    Date release_date
    Images images
    Keywords keywords
    AlternativeTitles alternativeTitles

    String getUrl() {
        "${MOVIE_URL}/${id}"
    }
}
