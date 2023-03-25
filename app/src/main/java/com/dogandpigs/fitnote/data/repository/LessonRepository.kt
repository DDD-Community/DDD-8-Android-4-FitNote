package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.mapper.toData
import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.model.LessonIdRequest
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.dogandpigs.fitnote.data.util.handleResponse
import com.dogandpigs.fitnote.domain.model.Lesson
import com.google.gson.JsonObject
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonApi: LessonApi
) {
    suspend fun addLesson(lesson: Lesson): Int {
        lessonApi.addLesson(lesson.toData()).run {
            return body()?.data ?: -1
        }
    }

    suspend fun getIntendedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        return handleResponse(
            response = lessonApi.getIntendedLessonList(json),
            isCheck = false,
        )
    }

    suspend fun getCompletedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        return handleResponse(
            response = lessonApi.getCompletedLessonList(json),
            isCheck = false,
        )
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

    suspend fun deleteLesson(
        lessonId: Int,
    ): Int {
        lessonApi.deleteLesson(
            LessonIdRequest(
                lessonId = lessonId
            )
        ).run {
            return body()?.data ?: -1
        }
    }

    suspend fun updateLesson(lesson: Lesson): Int {
        lessonApi.updateLesson(lesson.toData()).run {
            return body()?.data ?: -1
        }
    }
}
