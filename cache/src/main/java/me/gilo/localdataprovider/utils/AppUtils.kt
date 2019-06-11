package me.gilo.localdataprovider.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.util.Base64
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/*
 *
 */
class AppUtils(internal var context: Context) {

    companion object {
        val currentDateTime: Date
            get() = Calendar.getInstance().time


        fun generateHash(password: String): String? {
            var md: MessageDigest? = null
            try {
                md = MessageDigest.getInstance("SHA-512")
                md!!.update(password.toByteArray())
                val byteData = md.digest()
                return Base64.encodeToString(byteData, Base64.NO_WRAP)

            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return null
        }


    }
}