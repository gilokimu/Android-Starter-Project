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

    internal var token: String? = null
    internal var expiry: String? = null

    val isLoggedIn: Boolean
        get() {
            val prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE)
            return prefs.getBoolean("loggedIn", false)
        }

    fun saveToken(token: String, expiry: String) {
        val editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit()
        editor.putString("token", token)
        editor.putString("expiry", expiry)
        editor.putBoolean("loggedIn", true)
        editor.apply()
    }

    fun getToken(): String? {
        val prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE)
        return prefs.getString("token", null)
    }

    fun getExpiry(): String? {
        val prefs = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE)
        return prefs.getString("expiry", null)
    }

    fun logOut() {
        val editor = context.getSharedPreferences(MY_PREFS_NAME, context.MODE_PRIVATE).edit()
        editor.putString("token", "")
        editor.putString("expiry", "")
        editor.putBoolean("loggedIn", false)
        editor.apply()
    }

    companion object {

        val MY_PREFS_NAME = "StarterApp"

        fun showToast(context: Context, @StringRes text: Int, isLong: Boolean) {
            showToast(context, context.getString(text), isLong)
        }

        fun showToast(context: Context, text: String, isLong: Boolean) {
            Toast.makeText(context, text, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
        }

        val currentDateTime: Date
            get() = Calendar.getInstance().time

        fun getFormattedDateString(date: Date): String? {

            try {

                var spf = SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy")
                val dateString = spf.format(date)

                val newDate = spf.parse(dateString)
                spf = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
                return spf.format(newDate)

            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        }

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

        fun showMessage(context: Context, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }

        fun openKeyboard(context: Context) {
            Handler().postDelayed({
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
            }, 500)
        }

        fun hideKeyboard(activity: Activity) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }
}