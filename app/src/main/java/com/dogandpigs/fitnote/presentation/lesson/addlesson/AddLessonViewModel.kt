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
) : BaseViewModel<AddLessonState>() {
    override fun createInitialState(): AddLessonState = AddLessonState()
    
    
    fun setMemberId(memberId: Int) = currentState {
        setState { copy(id = memberId) }
    }
    
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
        val list = exerciseList.toMutableList()
        val routine = currentRoutine.copy(
            index = index,
            name = name
        )
        setState {
            copy(currentRoutine = routine)
        }
        list[index] = routine
        setState {
            copy(exerciseList = list)
        }
    }
    
    fun setLessonDate() = currentState {
        val list = exerciseList.toMutableList()
        val routine = currentRoutine.copy(
            today = dateString
        )
        setState {
            copy(currentRoutine = routine)
        }
        list[currentRoutine.index] = routine
        setState {
            copy(exerciseList = list)
        }
    }
    
    
    fun setLessonWeight(index: Int, weight: Int) = currentState {
        val list = exerciseList.toMutableList()
        val routine = currentRoutine.copy(
            weight = weight,
        )
        
        list[index] = routine
        setState {
            copy(currentRoutine = routine)
        }
        setState {
            copy(exerciseList = list)
        }
    }
    
    fun setLessonCount(index: Int, count: Int) = currentState {
        val list = exerciseList.toMutableList()
        val routine = currentRoutine.copy(
            count = count
        )
        list[index] = routine
        
        setState {
            copy(currentRoutine = routine)
        }
        setState {
            copy(exerciseList = list)
        }
    }
    
    fun setLessonSet(index: Int, set: Int) = currentState {
        val list = exerciseList.toMutableList()
        val routine = currentRoutine.copy(
            set = set
        )
        list[index] = routine
        setState {
            copy(currentRoutine = routine)
        }
        setState {
            copy(exerciseList = list)
        }
    }
    
    fun addAllLessons() = currentState {
        viewModelScope.launch {
            exerciseList.forEach { routine ->
                routine.id = id
                routine.today = dateString
                lessonRepository.addLesson(routine)
                Log.d(Constants.TAG_DEBUG, "addAllLessons: ${routine.name}")
            }
        }
    }
    
    private fun sortSet(routineList: MutableList<Routine>): MutableList<Routine> = currentState {
        for (i in routineList.indices) {
            routineList[i].set = i + 1
        }
        return routineList
    }
}
