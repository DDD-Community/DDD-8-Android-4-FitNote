package com.dogandpigs.fitnote.data.repository

import android.util.Log
import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
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

    suspend fun getCompletedLessons(id: Int): LessonResponse? {
        val json = JsonObject().apply {
            addProperty("id", id)
        }
        lessonApi.getCompletedLessonList(json).run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }

    suspend fun getLessonDetail(
        id: Int,
        today: Int,
    ): LessonDetailResponse? {

        Log.d("aa12", "getLessonDetail id : $id")
        Log.d("aa12", "getLessonDetail today : $today")

        val json = JsonObject().apply {
            addProperty("id", id)
            addProperty("today", today)
        }

        lessonApi.getLessonDetail(json).run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }
}
