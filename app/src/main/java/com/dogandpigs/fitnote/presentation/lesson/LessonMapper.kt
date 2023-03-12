package com.dogandpigs.fitnote.presentation.lesson

import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse

internal fun LessonDetailResponse?.toPresentation(
    id: Int,
    today: Int,
    tempListAction: ((id: Int, today: Int, lessonId: Int) -> Unit)? = null
): List<Exercise> =
    this?.run {
        this.lessonInfo.flatten().groupBy {
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
