package com.example.moviesrus.domain.models

import android.os.Parcelable
import com.example.moviesrus.data.dtos.GenreDto
import com.example.moviesrus.data.dtos.LanguageDto
import com.example.moviesrus.data.dtos.ProductionDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val genres: List<Genre>?,
    val production: List<Production>?,
    val languages: List<Language>?,
    val runtime: Int?,
): Parcelable
