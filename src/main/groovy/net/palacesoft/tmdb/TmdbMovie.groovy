package net.palacesoft.tmdb

import com.fasterxml.jackson.databind.ObjectMapper
import net.palacesoft.tmdb.model.*
import org.apache.commons.io.IOUtils

class TmdbMovie {

    private String apiKey
    private String apiVersion
    private String baseUrl = "http://api.themoviedb.org"
    private ObjectMapper mapper = new ObjectMapper()

    TmdbMovie(String apiKey, String apiVersion = "3") {
        this.apiKey = apiKey
        this.apiVersion = apiVersion
    }

    Movie getMovie(int movieId, boolean expandResults = false) {
        Movie movie = mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}?api_key=${apiKey}"""), Movie.class)
        if (expandResults) {
            movie.setImages(getImages(movieId))
            movie.setKeywords(getKeyWords(movieId))
            movie.setAlternativeTitles(getAlternativeTitles(movieId))
        }
        return movie
    }

    String getPosterUrlForSize(int movieId, String size) {
        Movie movie = mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}?api_key=${apiKey}"""), Movie.class)
        Image image = getConfiguration().images
        String posterPath = movie.poster_path

        assert image.poster_sizes.contains(size)

        "${image.base_url}${size}${posterPath}"
    }

    byte[] getPosterForSize(int movieId, String size) {
        def is = new URL(getPosterUrlForSize(movieId, size)).openStream()
        try {
            return IOUtils.toByteArray(is)
        } finally {
            IOUtils.closeQuietly(is)
        }
    }

    List<String> availableBackdropSizes() {
        Configuration configuration = getConfiguration()
        configuration.images.backdrop_sizes
    }

    List<String> availablePosterSizes() {
        Configuration configuration = getConfiguration()
        configuration.images.poster_sizes
    }

    Configuration getConfiguration() {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/configuration?api_key=${apiKey}"""),
                Configuration.class)
    }

    Images getImages(int movieId) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}/images?api_key=${apiKey}"""), Images.class)
    }

    Keywords getKeyWords(int movieId) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}/keywords?api_key=${apiKey}"""), Keywords.class)
    }

    Movies getPopular(int page = 1) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/popular?api_key=${apiKey}&page=${page}"""), Movies.class)
    }

    Movies getTopRated(int page = 1) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/top_rated?api_key=${apiKey}&page=${page}"""), Movies.class)
    }

    AlternativeTitles getAlternativeTitles(int movieId) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}/alternative_titles?api_key=${apiKey}"""), AlternativeTitles.class)
    }

    List<Movie> search(String query, int noResults = 3, boolean expandResults = false) {
        SearchMovieResult result = mapper.readValue(new URL("""${baseUrl}/${apiVersion}/search/movie?query=${URLEncoder.encode(query, "utf-8")}&api_key=${apiKey}"""),
                SearchMovieResult.class)

        def results = result.results
        if (results.size() >= noResults) {
            results = results[0..noResults]
        }

        results.collect {getMovie(it.id, expandResults)}

    }

    Movies getUpcoming(int page = 1) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/upcoming?api_key=${apiKey}&page=${page}"""), Movies.class)
    }

    Movie getLatest() {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/latest?api_key=${apiKey}"""), Movie.class)
    }

    Movies getSimilar(int movieId, int page = 1) {
        mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}/similar_movies?api_key=${apiKey}&page=${page}"""), Movies.class)
    }
}
