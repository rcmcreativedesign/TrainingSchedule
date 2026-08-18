package com.example.educationpath.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): java.util.Date?
        return value == null ? null : new java.util.Date(value)

    @TypeConverter
    fun dateToTimestamp(date: java.util.Date?):
        return date == null ? null : date.time
}