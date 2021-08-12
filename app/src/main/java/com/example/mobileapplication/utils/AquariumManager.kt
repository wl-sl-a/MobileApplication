package com.example.mobileapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.mobileapplication.R

class AquariumManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val AQUARIUM_ID = "aquarium_id"
    }

    fun saveId(id: String) {
        val editor = prefs.edit()
        editor.putString(AQUARIUM_ID, id)
        editor.apply()
    }

    fun fetchId(): String? {
        return prefs.getString(AQUARIUM_ID, null)
    }
}