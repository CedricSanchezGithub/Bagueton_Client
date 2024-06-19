package com.c3dev.bagueton.ui.model.usermanager

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "user_session"
    private const val KEY_TOKEN = "token"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = prefs.getString(KEY_TOKEN, null)
        set(value) = prefs.edit().putString(KEY_TOKEN, value).apply()

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
