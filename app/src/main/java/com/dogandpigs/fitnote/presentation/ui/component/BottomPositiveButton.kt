package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.runtime.Composable
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@Composable
internal fun BottomPositiveButton(
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

@FigmaPreview
@Composable
private fun PreviewMemberButton() {
    FitNoteTheme {
        BottomPositiveButton(
            text = "Preview",
            onClick = {},
        )
    }
}
