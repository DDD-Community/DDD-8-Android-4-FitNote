package com.dogandpigs.fitnote.presentation.member.component

import androidx.compose.runtime.Composable
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@Composable
internal fun MemberButton(
    text: String,
    onClick: () -> Unit,
) {
    DefaultBottomLargePositiveButton(
        positiveText = text,
        onClickPositive = onClick,
    ) {
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
    }
}
