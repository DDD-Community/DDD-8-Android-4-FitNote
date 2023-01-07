package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor() : BaseViewModel<AddLessonUiState>() {
    override fun createInitialState(): AddLessonUiState = AddLessonUiState()

    fun addRoutine(routine: Routine) = currentState {
        val routineList = routineList.toMutableList()
        if (routineList.isNotEmpty()) {
            routine.set = routineList.last().set + 1
        }
        routineList.add(routine)
        setState { copy(routineList = routineList) }
    }

    fun removeRoutine(set: Int) = currentState {
        val routineList = routineList.toMutableList()
        val routine = routineList.find { routine ->
            routine.set == set
        }
        routineList.remove(routine)
        setState { copy(routineList = routineList) }
    }
}
