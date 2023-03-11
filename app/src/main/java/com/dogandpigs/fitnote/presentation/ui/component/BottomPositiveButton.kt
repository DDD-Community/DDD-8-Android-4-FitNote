package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun BottomPositiveButton(
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        DefaultPositiveButton(
            positiveText = text,
            buttonTextStyle = LocalFitNoteTypography.current.buttonDefault,
            onClickPositive = onClick,
        )
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
