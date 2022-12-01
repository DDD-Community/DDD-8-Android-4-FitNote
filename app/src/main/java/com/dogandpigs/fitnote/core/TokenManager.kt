package com.dogandpigs.fitnote.core

/**
 * 토큰 관리 어떻게 할까요?
 * */
object TokenManager {
    var accessToken: String? = null
        private set
    
    fun setAccessToken(token: String) {
        accessToken = token
    }
}