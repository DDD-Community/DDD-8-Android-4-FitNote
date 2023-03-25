package com.dogandpigs.fitnote.presentation.base

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface MainEvent {
    object None : MainEvent

    data class CustomToast(
        val message: String,
        val bottomPadding: Dp = 0.dp,
    ) : MainEvent
}
