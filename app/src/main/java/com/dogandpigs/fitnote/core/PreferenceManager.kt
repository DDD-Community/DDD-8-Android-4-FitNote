package com.dogandpigs.fitnote.core

import android.content.Context

class PreferenceManager(application: FNApplication) {
    private val pref = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String? = null): String? {
        return pref.getString(key, defValue)
    }

    fun setString(key: String, str: String) {
        pref.edit().putString(key, str).apply()
    }

    fun clear() {
        pref.edit().clear().apply()
    }

    companion object {
        private const val PREF_NAME = "shared_pref"
        const val KEY_ACCESS_TOKEN = "key_access_token"
    }
}
