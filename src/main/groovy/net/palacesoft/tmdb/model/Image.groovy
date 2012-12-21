package net.palacesoft.tmdb.model


class Image {
    String base_url
    String secure_base_url
    List<String> poster_sizes
    List<String> backdrop_sizes
    List<String> profile_sizes
    List<String> logo_sizes

    String file_path
    int width
    int height
    String iso_639_1
    float aspect_ratio
    float vote_average
    int vote_count

}
