package com.dogandpigs.fitnote.data.source.remote.api

import com.dogandpigs.fitnote.data.source.remote.model.ResBase
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LessonApi {
    @POST
    suspend fun addLesson(@Body lesson: Routine): Response<ResBase<Int>>
    
    @POST
    suspend fun getIntendedLessonList(@Body id: Int): Response<ResBase<HashMap<String, Any>>>
}