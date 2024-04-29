package com.example.gymplan.data.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun listStringToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToListString(value: String): List<String> {
        return value.split(",").map { it }
    }
}