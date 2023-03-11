package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray1
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun BottomPositiveButton(
    text: String,
    enabled: Boolean = true,
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
            positiveButtonColor = if (enabled) {
                BrandPrimary
            } else {
                GrayScaleMidGray1
            },
            buttonTextStyle = LocalFitNoteTypography.current.buttonDefault,
            onClickPositive = {
                if (enabled) {
                    onClick()
                }
            },
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
