package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite

@Composable
internal fun DefaultTwoButton(
    positiveText: String,
    onClickPositive: () -> Unit,
    negativeText: String,
    onClickNegative: () -> Unit,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        DefaultNegativeButton(
            modifier = Modifier
                .weight(1F)
                .wrapContentHeight(),
            negativeText = negativeText,
            onClickNegative = onClickNegative,
        )
        WidthSpacer(width = 9.dp)
        DefaultPositiveButton(
            modifier = Modifier
                .weight(1F)
                .wrapContentHeight(),
            positiveText = positiveText,
            onClickPositive = onClickPositive,
        )
    }
}

@Composable
private fun DefaultNegativeButton(
    modifier: Modifier,
    negativeText: String,
    onClickNegative: () -> Unit,
) {
    DefaultButton(
        modifier = modifier,
        text = negativeText,
        textColor = GrayScaleMidGray3,
        buttonColor = Color.Transparent,
        borderColor = GrayScaleLightGray2,
        onClick = onClickNegative,
    )
}

@Composable
private fun DefaultPositiveButton(
    modifier: Modifier,
    positiveText: String,
    onClickPositive: () -> Unit,
) {
    DefaultButton(
        modifier = modifier,
        text = positiveText,
        textColor = GrayScaleWhite,
        buttonColor = BrandPrimary,
        borderColor = BrandPrimary,
        onClick = onClickPositive,
    )
}

@Composable
private fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    buttonColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = textColor
        )
    }
}

@FigmaPreview
@Composable
private fun PreviewDefaultTwoButton() {
    DefaultTwoButton(
        positiveText = "positiveText",
        onClickPositive = {},
        negativeText = "negativeText",
        onClickNegative = {}
    )
}
