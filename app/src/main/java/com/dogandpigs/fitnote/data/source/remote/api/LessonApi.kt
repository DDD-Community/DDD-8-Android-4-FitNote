package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.model.LessonResponse
import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LessonApi {
    @POST("/hypeboy/add/")
    suspend fun addLesson(@Body lesson: Routine): Response<ResBase<Int>>
    
    @POST("/hypeboy/ing/")
    suspend fun getIntendedLessonList(@Body request: JsonObject): Response<ResBase<LessonResponse>>
    
    @POST("/hypeboy/end/")
    suspend fun getCompletedLessonList(@Body request: JsonObject): Response<ResBase<LessonResponse>>

    @POST("/hypeboy/info/")
    suspend fun getLessonDetail(@Body request: JsonObject): Response<ResBase<LessonDetailResponse>>
}
