Groovy wrapper to access http://docs.themoviedb.apiary.io/

Example:

```groovy
TmdbMovie tmdbMovie = new TmdbMovie(movieKey)
Movie movie = tmdbMovie.search(node.getProperty("title").toString(), 1)[0]

def movieUrl = movie.getUrl()
def poster = tmdbMovie.getPosterUrlForSize(movie.id, "w185")
def rating = movie.vote_average
def tagLine = movie.tagline
def overview = movie.overview
```
