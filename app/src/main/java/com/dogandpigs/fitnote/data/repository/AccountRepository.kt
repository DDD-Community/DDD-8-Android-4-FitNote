package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.AccountApi
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.UserDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val api: AccountApi
) {
    suspend fun join(data: JoinRequestDTO): Flow<Response<UserDTO>> {
        return api.join(data)
    }
    
    suspend fun login(data: LoginRequestDTO): Flow<Response<UserDTO>> {
        return api.login(data)
    }
}