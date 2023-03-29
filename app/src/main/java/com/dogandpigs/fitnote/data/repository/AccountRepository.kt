package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.core.TokenManager
import com.dogandpigs.fitnote.data.source.remote.api.AccountApi
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequest
import com.dogandpigs.fitnote.data.source.remote.model.UserDTO
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val api: AccountApi
) {
    suspend fun join(data: JoinRequest): UserDTO? {
        api.join(data).run {
            if (!isSuccessful || body() == null) {
                return null
            }
            return body()
        }
    }

    suspend fun login(data: LoginRequest): UserDTO? {
        api.login(data).run {
            check(isSuccessful)
            val body = body()
            checkNotNull(body)
            val accessToken = body.accessToken
            checkNotNull(accessToken)
            TokenManager.setAccessToken(accessToken)
            return body()
        }
    }
}
