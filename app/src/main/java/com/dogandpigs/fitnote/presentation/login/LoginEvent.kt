package com.dogandpigs.fitnote.presentation.login

internal sealed interface LoginEvent {
    object None : LoginEvent
    object LoginFail : LoginEvent
    object FindPassword : LoginEvent
}
