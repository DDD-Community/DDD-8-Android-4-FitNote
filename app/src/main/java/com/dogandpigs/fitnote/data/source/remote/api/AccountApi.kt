package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequest
import com.dogandpigs.fitnote.data.source.remote.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("/accounts/")
    suspend fun join(@Body data: JoinRequest): Response<UserDTO>
    
    @POST("/accounts/login/")
    suspend fun login(@Body data: LoginRequest): Response<UserDTO>
}