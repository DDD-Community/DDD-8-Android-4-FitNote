package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.core.FormatUtil
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor() : BaseViewModel() {
    val uiState: MutableStateFlow<AddLessonUiState> = MutableStateFlow(AddLessonUiState())

    init {
        initialize()
    }

    private fun initialize() {
        setDate(System.currentTimeMillis())
    }

    fun setDate(date: Long) {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                dateString = FormatUtil.millToDate(date)
            )
        }
    }

    fun addRoutine(routine: Routine) {
        val routineList = uiState.value.routineList.toMutableList()
        if (routineList.isNotEmpty()) {
            routine.set = routineList.last().set + 1
        }
        routineList.add(routine)
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                routineList = routineList
            )
        }
    }

    fun removeRoutine(set: Int) {
        val routineList = uiState.value.routineList.toMutableList()
        val routine = routineList.find { routine ->
            routine.set == set
        }
        routineList.remove(routine)
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                routineList = routineList
            )
        }
    }
}
