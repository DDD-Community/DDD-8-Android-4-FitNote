package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.JoinRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequestDTO
import com.dogandpigs.fitnote.data.source.remote.model.UserDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("/accounts/")
    suspend fun join(@Body data: JoinRequestDTO): Flow<Response<UserDTO>>
    
    @POST("/accounts/login")
    suspend fun login(@Body data: LoginRequestDTO): Flow<Response<UserDTO>>
}