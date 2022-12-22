package com.example.moviesrus.data.remote

import com.example.moviesrus.data.dtos.GenreDto
import com.example.moviesrus.data.dtos.ListResultDto
import com.example.moviesrus.data.dtos.ListResultGenreDto
import com.example.moviesrus.data.dtos.MovieDto
import com.example.moviesrus.util.API_KEY
import com.example.moviesrus.util.API_LANGUAGE
import com.example.moviesrus.util.API_SORT_BY_POPULARITY_DESC
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE,
    ): ListResultGenreDto<GenreDto>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE,
        @Query("sort_by") sortBy: String = API_SORT_BY_POPULARITY_DESC,
    ): ListResultDto<MovieDto>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = API_LANGUAGE,
    ): ListResultDto<MovieDto>

}