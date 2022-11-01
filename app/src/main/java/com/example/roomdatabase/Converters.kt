package com.example.roomdatabase

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.*

class Converters {

    @TypeConverter
    fun fromDateToLong(value: Date):Long {  // Run when saving of Date taken place
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value:Long): Date{   // Run when Date will be fetched
        return Date(value)
    }
}