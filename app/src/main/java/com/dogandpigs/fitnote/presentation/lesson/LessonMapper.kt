package com.dogandpigs.fitnote.presentation.lesson

import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.domain.model.Lesson

internal fun LessonDetailResponse?.toPresentation(
    id: Int,
    today: Int,
    tempListAction: ((id: Int, today: Int, lessonId: Int) -> Unit)? = null,
    mode: LessonMode = LessonMode.UNKNOWN,
): List<Exercise> =
    this?.run {
        this.lessonInfo.flatten().let {
            if (mode == LessonMode.SHOW || mode == LessonMode.ADD || mode == LessonMode.EDIT) {
                it.filter { lesson ->
                    lesson.completion == 0
                }
            } else {
                it
            }
        }.groupBy {
            it.name
        }.let { maps ->
            val exerciseList = mutableListOf<Exercise>()
            maps.forEach { (name, lessonDescriptionList) ->
                exerciseList.add(
                    Exercise(
                        name = name,
                        sets = lessonDescriptionList.let {
                            val list = mutableListOf<Exercise.ExerciseSet>()
                            it.forEach { lessonDescription ->
                                list.add(
                                    Exercise.ExerciseSet(
                                        lessonId = lessonDescription.lessonId,
                                        setIndex = lessonDescription.set,
                                        weight = lessonDescription.weight.toDouble(),
                                        count = lessonDescription.count,
                                        // TODO
                                        isDone = false,
                                    )
                                )
                                tempListAction?.invoke(
                                    id,
                                    today,
                                    lessonDescription.lessonId,
                                )
                            }
                            list
                        },
                        isFold = false,
                    )
                )
            }
            exerciseList
        }
    } ?: emptyList()

internal fun Exercise.ExerciseSet.toLesson(
    id: Int,
    name: String,
    today: String,
): Lesson =
    Lesson(
        id = id,
        lessonId = this.lessonId,
        name = name,
        set = this.setIndex,
        weight = this.weight.toString(),
        count = this.count,
        today = today,
    )
