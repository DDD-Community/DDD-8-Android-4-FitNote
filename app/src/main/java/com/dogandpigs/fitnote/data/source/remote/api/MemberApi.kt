package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import com.dogandpigs.fitnote.data.source.remote.request.MemberRequest
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.dogandpigs.fitnote.data.source.remote.response.ListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST

interface MemberApi {
    @POST("/attention/list/")
    suspend fun getMemberList(): Response<ResBase<ListResponse>>

    @POST("/attention/add/")
    suspend fun addMember(@Body memberRequest: MemberRequest): Response<ResBase<Any>>

    @POST("/attention/info/")
    suspend fun getMemberInfo(): Response<ResBase<LessonResponse>>

    @DELETE("/attention/delete/")
    suspend fun deleteTrainer(): Response<ResBase<Int>>
}
