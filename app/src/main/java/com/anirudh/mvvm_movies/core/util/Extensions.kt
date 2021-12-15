package com.anirudh.mvvm_movies.core.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.formatTime(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val formatter = SimpleDateFormat("d MMM, yyyy", Locale.ENGLISH)
    return try {
        formatter.format(parser.parse(this))
    } catch (e: Exception){
        "Failed to parse"
    }
}

fun Int.toHoursAndMins(): String {
    val hours: Int = this / 60 //since both are ints, you get an int
    val minutes: Int = this % 60
    System.out.printf("%d:%02d", hours, minutes)
    return ""+hours+"h "+minutes+"min"
}