package com.example.mobileapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mobileapplication.R

class NotificationManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val HOUR = "hour"
        const val MINUTE = "minute"
    }

    fun save(id: String) {
        val editor = prefs.edit()
        editor.putString(HOUR, id)
        editor.apply()
    }

    fun fetch(): String? {
        return prefs.getString(MINUTE, null)
    }
}