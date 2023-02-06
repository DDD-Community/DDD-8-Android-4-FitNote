package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.LessonApi
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonApi: LessonApi
) {
    suspend fun addLesson(routine: Routine): Int {
        lessonApi.addLesson(routine).run {
            return body()?.data ?: -1
        }
    }
    
    suspend fun getIntendedLessons(id: Int): HashMap<String, Any>? {
        lessonApi.getIntendedLessonList(id).run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }
}