package me.gilo.localdataprovider.utils

import me.gilo.localdataprovider.utils.DateUtils.hourMinuteFormat
import org.joda.time.DateMidnight
import org.joda.time.DateTime
import org.joda.time.Days

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    internal var dateFormat = SimpleDateFormat("EEE hh:mma MMM d, yyyy")
    internal var longagoDateFormat = SimpleDateFormat("dd/MM/yyyy")
    internal var weekdayFormat = SimpleDateFormat("EEE")
    internal var hourMinuteFormat = SimpleDateFormat("hh:mm a")

    private fun isToday(dateTime: DateTime): Boolean {
        val today = DateMidnight()
        return today == dateTime.toDateMidnight()
    }

    private fun isYesterday(dateTime: DateTime): Boolean {
        val yesterday = DateMidnight().minusDays(1)
        return yesterday == dateTime.toDateMidnight()
    }

    private fun getDayString(date: Date): String {
        val s: String

        if (isToday(DateTime(date)))
            s = "Today"
        else if (isYesterday(DateTime(date)))
            s = "Yesterday"
        else
            s = weekdayFormat.format(date)

        return s
    }

    fun getDateString_shortAndSmart(date: Date): String {
        val s: String

        val nowDT = DateTime()
        val dateDT = DateTime(date)
        val days = Days.daysBetween(dateDT, nowDT).days

        if (isToday(DateTime(date)))
            s = getHourMinuteString(date)
        else if (days < 7)
            s = getDayString(date)
        else
            s = getDateString(date)

        return s
    }


    fun getDateStatus(date: Date?): String {
        val s: String

        if (date == null)
            return "Null"

        val nowDT = DateTime()
        val dateDT = DateTime(date)
        val days = Days.daysBetween(dateDT, nowDT).days

        if (isToday(DateTime(date)))
            s = getHourMinuteString(date)
        else if (days < 7)
            s = getDayString(date) + "at " + getHourMinuteString(date)
        else
            s = getDateString(date)

        return "Last online $s"
    }

    private fun getDateString(date: Date): String {
        return longagoDateFormat.format(date)
    }

    private fun getHourMinuteString(date: Date): String {

        return hourMinuteFormat.format(date)
    }
}
