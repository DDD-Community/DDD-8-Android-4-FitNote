package com.dogandpigs.fitnote.presentation.login

internal sealed interface LoginEvent {
    object None : LoginEvent
    object Toast : LoginEvent
}
