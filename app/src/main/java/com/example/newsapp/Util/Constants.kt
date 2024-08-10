package com.example.newsapp.Util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Constants {
    companion object {
        const val API_KEY = "6042c8496005432fb7dbc96ecdbbaafa"
        const val BASE_URL = "https://newsapi.org/"
        const val SEARCH_NEWS_TIME_DELAY = 500L
        const val QUERY_PAGE_SIZE = 20

        fun convertDateTime(isoDate: String): String {
            val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date: Date? = isoFormat.parse(isoDate)

            val outputFormat = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getDefault()

            return date?.let { outputFormat.format(it) } ?: "Invalid date"
        }
    }
}