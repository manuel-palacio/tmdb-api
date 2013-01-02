Groovy wrapper to access http://docs.themoviedb.apiary.io/

Example:

```groovy
TmdbMovie tmdbMovie = new TmdbMovie(movieKey)
Movie movie = tmdbMovie.search("fight club")[0]

def movieUrl = movie.getUrl()
def rating = movie.vote_average
def tagLine = movie.tagline
def overview = movie.overview
def poster = tmdbMovie.getPosterUrlForMovieWithSize(movie.id, "w185")
Movies movies = tmdbMovie.getSimilar(movie.id)
byte[] poster = tmdbMovie.getPosterForMovieWithSizeAsBytes(movie.id, "w342")
```
