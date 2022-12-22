package com.example.moviesrus.data.remote.repository

import com.example.moviesrus.data.dtos.mappers.toDomain
import com.example.moviesrus.data.remote.MovieApi
import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.domain.repository.MovieRepository
import com.example.moviesrus.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getGenres(): Flow<Resource<List<Genre>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val genres = api.getGenres().genres
        with(genres) {
            emit(Resource.Success(data = this.map { it.toDomain() }))
        }
    }.catch { e ->
        emit(Resource.Error(message = e.message.toString()))
    }.flowOn(Dispatchers.IO)

    override suspend fun getMoviesByGenre(genreId: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val genres = api.getMoviesByGenre(genreId = genreId).results
        with(genres) {
            emit(Resource.Success(data = this.map { it.toDomain() }))
        }
    }.catch { e ->
        emit(Resource.Error(message = e.message.toString()))
    }.flowOn(Dispatchers.IO)

    override suspend fun searchMovie(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading(isLoading = true))

        val genres = api.searchMovie(query = query).results
        with(genres) {
            emit(Resource.Success(data = this.map { it.toDomain() }))
        }
    }.catch { e ->
        emit(Resource.Error(message = e.message.toString()))
    }.flowOn(Dispatchers.IO)
}