package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.AccountApi
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val api: AccountApi
) {
    suspend fun join(data: JoinRequestDTO): Flow<UserDTO?> {
        api.join(data).run {
            if (!isSuccessful || body() == null) {
                return flowOf(null).flowOn(Dispatchers.IO)
            }
            return flowOf(body()).flowOn(Dispatchers.IO)
        }
    }
    
    suspend fun login(data: LoginRequestDTO): Flow<UserDTO?> {
        api.login(data).run {
            if (!isSuccessful || body() == null) {
                return flowOf(null).flowOn(Dispatchers.IO)
            }
            return flowOf(body()).flowOn(Dispatchers.IO)
        }
    }
}