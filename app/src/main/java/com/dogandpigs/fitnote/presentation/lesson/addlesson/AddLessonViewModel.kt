package com.dogandpigs.fitnote.presentation.lesson.addlesson

import android.os.Build
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.core.Constants
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : BaseViewModel<AddLessonUiState>() {
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
        setState { copy(routineList = sortSet(routineList)) }
    }
    
    fun addLesson(routine: Routine) = currentState {
        val exerciseList = exerciseList.toMutableList()
        if (exerciseList.isNotEmpty()) {
            routine.index = exerciseList.last().index + 1
        }
        exerciseList.add(routine)
        setState {
            copy(
                exerciseList = exerciseList
            )
        }
        exerciseList.forEach {
            Log.d(Constants.TAG_DEBUG, "addLesson: ${it.index}")
        }
        viewModelScope.launch {
//            lessonRepository.addLesson(routine)
        }
    }
    
    fun removeExercise(index: Int, name: String) = currentState {
        val removedList = exerciseList.toMutableList()
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            removedList.removeIf {
                it.index == index
            }
            
            removedList.forEach {
                Log.d(Constants.TAG_DEBUG, "removeExercise: ${it.index} / ${it.name}")
            }
            setState {
                copy(exerciseList = removedList)
            }
        }
    }
    
    fun setLessonName(index: Int, name: String) = currentState {
        Log.d(Constants.TAG_DEBUG, "setLessonName: [$index] ${name}")
        val list = exerciseList.toMutableList()
        val routine = Routine(
            name = name,
            index = index
        )
        list[index] = routine
        
        setState {
            copy(exerciseList = list)
        }
    }
    
    private fun sortSet(routineList: MutableList<Routine>): MutableList<Routine> = currentState {
        for (i in routineList.indices) {
            routineList[i].set = i + 1
        }
        return routineList
    }
}
