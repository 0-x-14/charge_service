package com.example.charge_service

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    companion object {
        private const val PREFS_NAME = "MyPreferences"
        private const val KEY_USER_ID = "userID"
        // Add other keys as needed
    }

    fun getString(key: String, defaultValue: String): String? {
        return preferences.getString(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    // Add more methods as needed for other data types (Int, Boolean, etc.)

    // Example for boolean preferences
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    // Clear all preferences
    fun clearPreferences() {
        editor.clear()
        editor.apply()
    }
}
