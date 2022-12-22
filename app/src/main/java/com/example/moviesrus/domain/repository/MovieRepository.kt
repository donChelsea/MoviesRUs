package com.example.moviesrus.domain.repository

import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getGenres(): Flow<Resource<List<Genre>>>

    suspend fun getMoviesByGenre(genreId: String): Flow<Resource<List<Movie>>>
}