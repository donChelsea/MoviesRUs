package com.example.moviesrus.domain.repository

import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.domain.models.Video
import com.example.moviesrus.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getGenres(): Flow<Resource<List<Genre>>>

    suspend fun getMoviesByGenre(genreId: Int): Flow<Resource<List<Movie>>>

    suspend fun searchMovie(query: String): Flow<Resource<List<Movie>>>

    suspend fun getMovie(movieId: Int): Flow<Resource<Movie>>

    suspend fun getVideos(movieId: Int): Flow<Resource<List<Video>>>

}