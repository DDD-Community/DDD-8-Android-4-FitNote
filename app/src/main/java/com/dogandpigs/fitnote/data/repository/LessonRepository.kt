package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.dogandpigs.fitnote.data.util.handleResponse
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
        return handleResponse(lessonApi.getIntendedLessonList(json))
    }

    suspend fun getCompletedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        return handleResponse(lessonApi.getCompletedLessonList(json))
    }

    suspend fun getLessonDetail(
        id: Int,
        today: Int,
    ): LessonDetailResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
            addProperty("today", today)
        }

        return handleResponse(lessonApi.getLessonDetail(json))
    }

    suspend fun putLessonComplete(
        id: Int,
        lessonId: Int,
        today: Int,
    ) {
        val json = JsonObject().apply {
            addProperty("id", id)
            addProperty("lesson_id", lessonId)
            addProperty("today", today)
        }

        lessonApi.putLessonComplete(json)
    }
}
