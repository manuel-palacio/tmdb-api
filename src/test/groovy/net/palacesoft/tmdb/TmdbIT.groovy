package net.palacesoft.tmdb

import net.palacesoft.tmdb.model.Movie
import org.junit.Test


class TmdbIT {

    def apiKey = System.getProperty("apiKey")

    def tmdbCast = new TmdbCast(apiKey)

    def movieApi = new TmdbMovie(apiKey)

    @Test
    public void castOps() {

        assert !tmdbCast.getCastForMovie(550).crew.empty

        assert !tmdbCast.getCastForMovie(550).cast.empty

        assert !tmdbCast.searchPerson("Brad Pitt").results.empty
    }

    @Test
    public void movies() {

        Movie movie = movieApi.getMovie(550)

        assert movie.backdrop_path

        assert movie.getUrl()

        assert !movieApi.getAlternativeTitles(550).titles.empty

        assert !movieApi.getPopular().results.empty

        assert !movieApi.getTopRated().results.empty

        assert !movieApi.getKeyWords(550).keywords.empty

        assert !movieApi.getUpcoming().results.empty

        assert !movieApi.getSimilar(550).results.empty

        assert movieApi.getLatest()

    }

    @Test
    public void images() {
        assert movieApi.getPosterUrlForMovieWithSize(550, "w342")

        assert movieApi.availableBackdropSizes()

        assert movieApi.availablePosterSizes()

        assert !movieApi.getImages(550).backdrops*.file_path.empty

        assert !movieApi.getImages(550).posters*.file_path.empty

        assert movieApi.getPosterForMovieWithSizeAsBytes(550, "w342").length > 0

    }

    @Test
    public void search() {
        def movie = movieApi.search("Father of the Bride", 1)[0]

        assert movie.title

        assert movie.tagline

        assert movie.overview

        assert movie.vote_average

        assert movie.id
    }
}
