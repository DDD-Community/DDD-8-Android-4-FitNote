package com.dogandpigs.fitnote.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel<STATE> : ViewModel() {
    abstract fun createInitialState(): STATE
    private val initialState: STATE by lazy { createInitialState() }

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun setState(reduce: STATE.() -> STATE) {
        val newState = state.value.reduce()
        _state.value = newState
    }

    protected inline fun <reified T> currentState(action: STATE.() -> T): T {
        return state.value.action()
    }

    private val _errorHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    protected open fun handleException(exception: Throwable) {
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        errorHandler: CoroutineExceptionHandler = _errorHandler,
        block: suspend CoroutineScope.() -> Unit
    ): Job = (viewModelScope + errorHandler).launch(context, start, block)
}
