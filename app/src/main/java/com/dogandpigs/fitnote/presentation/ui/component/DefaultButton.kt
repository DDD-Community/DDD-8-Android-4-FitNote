package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun DefaultTwoButton(
    reverseModifier: Modifier.() -> Modifier = { Modifier },
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
            .reverseModifier()
    ) {
        negativeText?.also {
            DefaultNegativeButton(
                modifier = Modifier
                    .weight(1F)
                    .wrapContentHeight(),
                negativeText = it,
                onClickNegative = {
                    onClickNegative?.invoke()
                },
            )
        }
        WidthSpacer(spaceBetweenButtons)
        positiveText?.also {
            DefaultPositiveButton(
                modifier = Modifier
                    .weight(1F)
                    .wrapContentHeight(),
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
internal fun DefaultNegativeButton(
    modifier: Modifier = Modifier,
    negativeText: String,
    buttonTextStyle: TextStyle? = null,
    onClickNegative: () -> Unit,
) {
    val buttonModifier = modifier
        .padding(
            horizontal = LocalFitNoteSpacing.current.spacing4,
        )
        .fillMaxWidth()
        .wrapContentHeight()

    if (buttonTextStyle != null) {
        DefaultButton(
            modifier = buttonModifier,
            text = negativeText,
            textColor = GrayScaleDarkGray1,
            style = buttonTextStyle,
            buttonColor = Color.Transparent,
            borderColor = GrayScaleMidGray2,
            onClick = onClickNegative,
        )
    } else {
        DefaultButton(
            modifier = buttonModifier,
            text = negativeText,
            textColor = GrayScaleMidGray3,
            buttonColor = Color.Transparent,
            borderColor = GrayScaleLightGray2,
            onClick = onClickNegative,
        )
    }
}

@Composable
internal fun DefaultPositiveButton(
    modifier: Modifier = Modifier,
    positiveText: String,
    positiveButtonColor: Color? = BrandPrimary,
    buttonTextStyle: TextStyle? = null,
    onClickPositive: () -> Unit,
) {
    val buttonModifier = modifier
        .padding(
            horizontal = LocalFitNoteSpacing.current.spacing4,
        )
        .fillMaxWidth()
        .wrapContentHeight()

    if (buttonTextStyle != null) {
        DefaultButton(
            modifier = buttonModifier,
            text = positiveText,
            textColor = GrayScaleWhite,
            style = buttonTextStyle,
            buttonColor = positiveButtonColor ?: BrandPrimary,
            borderColor = positiveButtonColor ?: BrandPrimary,
            onClick = onClickPositive,
        )
    } else {
        DefaultButton(
            modifier = buttonModifier,
            text = positiveText,
            textColor = GrayScaleWhite,
            buttonColor = positiveButtonColor ?: BrandPrimary,
            borderColor = positiveButtonColor ?: BrandPrimary,
            onClick = onClickPositive,
        )
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
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
private fun DefaultOutlinedButton(
    modifier: Modifier = Modifier,
    buttonColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
        ),
        border = BorderStroke(1.dp, borderColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        content()
    }
}

@Composable
private fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    buttonColor: Color,
    borderColor: Color,
    textSize: TextUnit = 12.sp,
    buttonPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 0.dp),
    onClick: () -> Unit,
) {
    DefaultOutlinedButton(
        modifier = modifier,
        buttonColor = buttonColor,
        borderColor = borderColor,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(buttonPadding),
            text = text,
            textAlign = TextAlign.Center,
            fontSize = textSize,
            color = textColor,
        )
    }
}

@Composable
private fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    style: TextStyle,
    buttonColor: Color,
    borderColor: Color,
    buttonPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 0.dp),
    onClick: () -> Unit,
) {
    DefaultOutlinedButton(
        modifier = modifier,
        buttonColor = buttonColor,
        borderColor = borderColor,
        onClick = onClick,
    ) {
        Text(
            modifier = Modifier.padding(buttonPadding),
            text = text,
            textAlign = TextAlign.Center,
            color = textColor,
            style = style,
        )
    }
}

@Composable
internal fun DefaultBottomPositiveButton(
    modifier: Modifier = Modifier,
    positiveText: String,
    buttonTextStyle: TextStyle = LocalFitNoteTypography.current.buttonDefault,
    onClickPositive: () -> Unit,
    content: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        DefaultPositiveButton(
            modifier = modifier,
            positiveText = positiveText,
            buttonTextStyle = buttonTextStyle,
            onClickPositive = onClickPositive,
        )
        content?.invoke()
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
