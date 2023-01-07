package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview

@Composable
internal fun WidthSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
internal fun HeightSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

internal fun Modifier.defaultBorder(): Modifier = composed {
    this.border(
        width = 1.dp,
        color = colorResource(id = R.color.border_gray),
        shape = RoundedCornerShape(10.dp)
    )
}

@FigmaPreview
@Composable
private fun PreviewDefaultBorderRow() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .defaultBorder()
    )
}
