package net.palacesoft.tmdb

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
    public void movieOps() {


        assert movieApi.getMovie(550).backdrop_path

        assert !movieApi.getAlternativeTitles(550).titles.empty

        def movie = movieApi.search("Fight Club", 1)[0]

        assert movie.title

        assert movie.tagline

        assert movie.overview

        assert movie.vote_average

        assert movie.id


        assert movieApi.getPosterUrlForSize(550, "w342")

        assert !movieApi.getPopular().results.empty

        assert movieApi.availableBackdropSizes()

        assert movieApi.availablePosterSizes()

        assert !movieApi.getTopRated().results.empty

        assert !movieApi.getImages(550).backdrops*.file_path.empty

        assert !movieApi.getImages(550).posters*.file_path.empty

        assert !movieApi.getKeyWords(550).keywords.empty

        assert !movieApi.getUpcoming().results.empty

        assert !movieApi.getSimilar(550).results.empty

        assert movieApi.getLatest()

        assert movieApi.getPosterForSize(550, "w342").length > 0

    }
}
