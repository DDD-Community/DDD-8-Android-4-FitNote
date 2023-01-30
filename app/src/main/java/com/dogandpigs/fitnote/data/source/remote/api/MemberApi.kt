package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.Member
import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import com.dogandpigs.fitnote.data.source.remote.model.TrainerInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MemberApi {
    @POST("/attention/list/")
    suspend fun getMemberList(): Response<ResBase<TrainerInfo>>
    
    @POST("/attention/add/")
    suspend fun addMember(@Body member: Member): Response<ResBase<Any>>
}