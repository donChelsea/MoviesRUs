package com.example.moviesrus.data.dtos.mappers

import com.example.moviesrus.data.dtos.GenreDto
import com.example.moviesrus.data.dtos.LanguageDto
import com.example.moviesrus.data.dtos.MovieDto
import com.example.moviesrus.data.dtos.ProductionDto
import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.models.Language
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.domain.models.Production

fun GenreDto.toDomain() = Genre(
    id = id,
    name = name
)

fun ProductionDto.toDomain() = Production(name)

fun LanguageDto.toDomain() = Language(name)

fun MovieDto.toDomain() = Movie(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    runtime = runtime,
    genres = genres?.map { it.toDomain() },
    production = production?.map { it.toDomain() },
    languages = languages?.map { it.toDomain() }
)