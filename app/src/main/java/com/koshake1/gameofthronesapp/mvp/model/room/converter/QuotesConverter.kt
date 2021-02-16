package com.koshake1.gameofthronesapp.mvp.model.room.converter

import androidx.room.TypeConverter

class QuotesConverter {
    @TypeConverter
    fun fromQuotes(quotes: List<String?>): String? {
        return quotes.joinToString(separator = "/")
    }

    @TypeConverter
    fun toQuotes(data: String): List<String?>? {
        return data.split("/").map { it.trim() }
    }
}