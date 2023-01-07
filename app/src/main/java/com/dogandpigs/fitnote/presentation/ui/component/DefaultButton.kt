package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite

@Composable
internal fun DefaultTwoButton(
    modifier: Modifier.() -> Modifier = { Modifier },
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    negativeText: String? = null,
    onClickNegative: (() -> Unit)? = null,
    spaceBetweenButtons: Dp = 9.dp,
    positiveButtonColor: Color? = null,
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .modifier()
    ) {
        negativeText?.also {
            DefaultNegativeButton(
                modifier = {
                    Modifier
                        .weight(1F)
                        .wrapContentHeight()
                },
                negativeText = it,
                onClickNegative = {
                    onClickNegative?.invoke()
                },
            )
        }
        WidthSpacer(spaceBetweenButtons)
        positiveText?.also {
            DefaultPositiveButton(
                modifier = {
                    Modifier
                        .weight(1F)
                        .wrapContentHeight()
                },
                positiveText = it,
                positiveButtonColor = positiveButtonColor,
                onClickPositive = {
                    onClickPositive?.invoke()
                },
            )
        }
    }
}

@Composable
private fun DefaultNegativeButton(
    modifier: Modifier.() -> Modifier = { Modifier },
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
    modifier: Modifier.() -> Modifier = { Modifier },
    positiveText: String,
    positiveButtonColor: Color? = BrandPrimary,
    onClickPositive: () -> Unit,
) {
    DefaultButton(
        modifier = modifier,
        text = positiveText,
        textColor = GrayScaleWhite,
        buttonColor = positiveButtonColor ?: BrandPrimary,
        borderColor = positiveButtonColor ?: BrandPrimary,
        onClick = onClickPositive,
    )
}

@Composable
fun PrimaryButton(
    modifier: Modifier.() -> Modifier = { Modifier },
    text: String,
    textSize: TextUnit = 16.sp,
    buttonPadding: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 0.dp),
    onClickPositive: () -> Unit,
) {
    DefaultButton(
        modifier = modifier,
        text = text,
        textColor = Color.White,
        buttonColor = BrandPrimary,
        borderColor = BrandPrimary,
        textSize = textSize,
        buttonPadding = buttonPadding,
        onClick = onClickPositive
    )
}

@Composable
private fun DefaultButton(
    modifier: Modifier.() -> Modifier = { Modifier },
    text: String,
    textColor: Color,
    buttonColor: Color,
    borderColor: Color,
    textSize: TextUnit = 12.sp,
    buttonPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 0.dp),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = Modifier
            .defaultMinSize(minWidth = 0.dp, minHeight = 0.dp)
            .background(Color.Transparent)
            .modifier(),
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            modifier = Modifier.padding(buttonPadding),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = textSize,
            color = textColor
        )
    }
}

@ComponentPreview
@Composable
private fun PreviewDefaultTwoButton() {
    DefaultTwoButton(
        positiveText = "positiveText",
        onClickPositive = {},
        negativeText = "negativeText",
        onClickNegative = {}
    )
}

@ComponentPreview
@Composable
private fun PreviewPrimaryButton() {
    PrimaryButton(text = "PrimaryButton") {

    }
}
