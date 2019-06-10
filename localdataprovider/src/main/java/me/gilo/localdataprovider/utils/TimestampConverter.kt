package me.gilo.localdataprovider.utils


import androidx.room.TypeConverter

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

object TimestampConverter {

    private val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        if (value != null) {
            try {
                val timeZone = TimeZone.getTimeZone("IST")
                df.timeZone = timeZone
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        } else {
            return null
        }
    }


    @TypeConverter
    fun dateToTimestamp(value: Date?): String? {
        val timeZone = TimeZone.getTimeZone("IST")
        df.timeZone = timeZone
        return if (value == null) null else df.format(value)
    }
}