package com.dogandpigs.fitnote.core

object TokenManager {
    var accessToken: String? = null
        private set
    
    fun setAccessToken(token: String) {
        accessToken = token
    }
}