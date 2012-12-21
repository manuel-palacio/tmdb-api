package net.palacesoft.tmdb

import com.fasterxml.jackson.databind.ObjectMapper
import net.palacesoft.tmdb.model.Casts
import net.palacesoft.tmdb.model.SearchPersonResult


class TmdbCast {

  private String apiKey
  private String apiVersion
  private String baseUrl = "http://api.themoviedb.org"
  ObjectMapper mapper = new ObjectMapper()

  TmdbCast(String apiKey, String apiVersion = "3") {
    this.apiKey = apiKey
    this.apiVersion = apiVersion
  }

  SearchPersonResult searchPerson(String name){
    mapper.readValue(new URL("""${baseUrl}/${apiVersion}/search/person?query=${URLEncoder.encode(name,"utf-8")}&api_key=${apiKey}"""),
            SearchPersonResult.class)
  }

  Casts getCastForMovie(int movieId){
    mapper.readValue(new URL("""${baseUrl}/${apiVersion}/movie/${movieId}/casts?api_key=${apiKey}"""), Casts.class)
  }

}
