package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.model.LessonResponse
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import com.google.gson.JsonObject
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonApi: LessonApi
) {
    suspend fun addLesson(routine: Routine): Int {
        lessonApi.addLesson(routine).run {
            return body()?.data ?: -1
        }
    }
    
    suspend fun getIntendedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        lessonApi.getIntendedLessonList(json).run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }
}