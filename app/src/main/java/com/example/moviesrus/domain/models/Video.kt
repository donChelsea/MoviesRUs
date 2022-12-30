package com.example.moviesrus.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val key: String,
    val site: String,
    val type: String,
) : Parcelable