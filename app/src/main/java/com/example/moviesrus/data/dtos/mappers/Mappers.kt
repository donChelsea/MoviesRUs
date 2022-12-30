package com.example.moviesrus.data.dtos.mappers

import com.example.moviesrus.data.dtos.*
import com.example.moviesrus.domain.models.*

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

fun VideoDto.toDomain() = Video(
    key = key,
    site = site,
    type = type
)