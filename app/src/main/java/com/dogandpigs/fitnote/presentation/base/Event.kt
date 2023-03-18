package com.dogandpigs.fitnote.presentation.base

internal sealed interface Event {
    object None : Event

    class Toast(
        val message: String
    ) : Event
}
