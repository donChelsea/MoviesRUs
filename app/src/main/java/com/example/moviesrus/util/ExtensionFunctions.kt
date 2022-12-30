package com.example.moviesrus.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun convertRuntime(time: Int): String {
    val hours: Int = time / 60 //since both are ints, you get an int
    val minutes: Int = time % 60
    return "${hours}h ${minutes}min"
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertReleasedDate(date: String): String {
    var date = LocalDate.parse(date)
    var formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy")
    return date.format(formatter).replace("-", " ")
}