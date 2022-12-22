package com.example.moviesrus.data.dtos.mappers

import com.example.moviesrus.data.dtos.GenreDto
import com.example.moviesrus.data.dtos.MovieDto
import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.models.Movie

fun GenreDto.toDomain() = Genre(
    id, name
)

fun MovieDto.toDomain() = Movie(
    adult, backdropPath, genreIds, id, title, overview, posterPath, releaseDate
)