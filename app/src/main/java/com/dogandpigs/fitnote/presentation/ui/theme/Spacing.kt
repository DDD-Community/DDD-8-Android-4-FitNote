package com.dogandpigs.fitnote.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class FitNoteDesignSystemSpacing constructor(
    val spacing1: Dp,
    val spacing2: Dp,
    val spacing3: Dp,
    val spacing4: Dp,
    val spacing5: Dp,
    val spacing6: Dp,
    val spacing7: Dp,
    val spacing8: Dp,
    val spacing9: Dp,
    val spacing10: Dp,
) {
    constructor(
        defaultSpacing: Dp = 4.dp,
        spacing1: Dp = 4.dp,
        spacing2: Dp = 8.dp,
        spacing3: Dp = 12.dp,
        spacing4: Dp = 16.dp,
        spacing5: Dp = 24.dp,
        spacing6: Dp = 32.dp,
        spacing7: Dp = 40.dp,
        spacing8: Dp = 60.dp,
        spacing9: Dp = 80.dp,
        spacing10: Dp = 100.dp,
    ) : this(
        spacing1 = spacing1,
        spacing2 = spacing2,
        spacing3 = spacing3,
        spacing4 = spacing4,
        spacing5 = spacing5,
        spacing6 = spacing6,
        spacing7 = spacing7,
        spacing8 = spacing8,
        spacing9 = spacing9,
        spacing10 = spacing10,
    )
}

val LocalFitNoteSpacing = staticCompositionLocalOf { FitNoteDesignSystemSpacing() }

@Composable
private fun PreviewSpacingItem(
    text: String,
    height: Dp,
) {
    Text(text = text)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(GrayScaleBlack)
    )
}

@Preview
@Composable
private fun PreviewSpacing() {
    FitNoteTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayScaleWhite)
                .verticalScroll(rememberScrollState())
        ) {
            val list = listOf(
                LocalFitNoteSpacing.current.spacing1 to "spacing1",
                LocalFitNoteSpacing.current.spacing2 to "spacing2",
                LocalFitNoteSpacing.current.spacing3 to "spacing3",
                LocalFitNoteSpacing.current.spacing4 to "spacing4",
                LocalFitNoteSpacing.current.spacing5 to "spacing5",
                LocalFitNoteSpacing.current.spacing6 to "spacing6",
                LocalFitNoteSpacing.current.spacing7 to "spacing7",
                LocalFitNoteSpacing.current.spacing8 to "spacing8",
                LocalFitNoteSpacing.current.spacing9 to "spacing9",
                LocalFitNoteSpacing.current.spacing10 to "spacing10",
            )
            for (item in list) {
                PreviewSpacingItem(
                    text = item.second,
                    height = item.first,
                )
            }
        }
    }
}
