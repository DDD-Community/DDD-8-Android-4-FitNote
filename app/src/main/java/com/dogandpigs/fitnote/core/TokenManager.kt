package com.dogandpigs.fitnote.core

/**
 * 토큰 관리 어떻게 할까요?
 * */
object TokenManager {
    var accessToken: String? = null
        private set
        get() = FNApplication.pref.getString(PreferenceManager.KEY_ACCESS_TOKEN)
    
    fun setAccessToken(token: String) {
        FNApplication.pref.setString(PreferenceManager.KEY_ACCESS_TOKEN, token)
    }
}