package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.mapper.toData
import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.model.LessonIdRequest
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.dogandpigs.fitnote.data.util.handleResponse
import com.dogandpigs.fitnote.data.util.handleResponseResBase
import com.dogandpigs.fitnote.domain.model.Lesson
import com.google.gson.JsonObject
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonApi: LessonApi
) {
    suspend fun addLesson(lesson: Lesson) {
        handleResponse(
            response = lessonApi.addLesson(lesson.toData()),
            isCheck = false,
        )?.let {
            when (it.statusCode) {
                "803" -> error("운동명을 입력해주세요.")
                "805" -> error("횟수를 입력해주세요.")
                else -> {}
            }
        } ?: error("ResBase is null")
    }

    suspend fun getIntendedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        return handleResponseResBase(
            response = lessonApi.getIntendedLessonList(json),
            isCheck = false,
        )
    }

    suspend fun getCompletedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        return handleResponseResBase(
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

        return handleResponseResBase(lessonApi.getLessonDetail(json))
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
