package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.model.LessonIdRequest
import com.dogandpigs.fitnote.data.source.remote.model.LessonRequest
import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.PUT

interface LessonApi {
    @POST("/hypeboy/add/")
    suspend fun addLesson(@Body lesson: LessonRequest): Response<ResBase<Int>>

    @POST("/hypeboy/ing/")
    suspend fun getIntendedLessonList(@Body request: JsonObject): Response<ResBase<LessonResponse>>

    @POST("/hypeboy/end/")
    suspend fun getCompletedLessonList(@Body request: JsonObject): Response<ResBase<LessonResponse>>

    @POST("/hypeboy/info/")
    suspend fun getLessonDetail(@Body request: JsonObject): Response<ResBase<LessonDetailResponse>>

    @PUT("/hypeboy/complet/")
    suspend fun putLessonComplete(@Body request: JsonObject): Response<ResBase<Int>>

    @HTTP(method = "DELETE", path = "/hypeboy/delete/", hasBody = true)
    suspend fun deleteLesson(@Body lessonId: LessonIdRequest): Response<ResBase<Int>>

    @POST("/hypeboy/update/")
    suspend fun updateLesson(@Body lesson: LessonRequest): Response<ResBase<Int>>
}
