package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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

@Composable
private fun getButtonPadding(
    vertical: Dp = LocalFitNoteSpacing.current.spacing4,
    horizontal: Dp = LocalFitNoteSpacing.current.spacing4,
): PaddingValues = PaddingValues(
    vertical = vertical,
    horizontal = horizontal,
)

@Composable
internal fun DefaultTwoButton(
    modifier: Modifier = Modifier,
    negativeText: String? = null,
    onClickNegative: (() -> Unit)? = null,
    negativePaddingValues: PaddingValues = PaddingValues(),
    negativeTextStyle: TextStyle? = null,
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    positivePaddingValues: PaddingValues = PaddingValues(),
    positiveTextStyle: TextStyle? = null,
    positiveButtonColor: Color? = null,
    spaceBetweenButtons: Dp = 0.dp,
) {
    val buttonPadding = PaddingValues(
        horizontal = if (negativeText == null || positiveText == null) {
            LocalFitNoteSpacing.current.spacing3
        } else {
            LocalFitNoteSpacing.current.spacing4
        }
    )

    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        negativeText?.also {
            DefaultNegativeButton(
                modifier = Modifier
                    .weight(1F)
                    .wrapContentHeight(),
                paddingValues = negativePaddingValues,
                negativeText = it,
                buttonTextStyle = negativeTextStyle,
                buttonPadding = buttonPadding,
                onClickNegative = {
                    onClickNegative?.invoke()
                },
            )
            WidthSpacer(spaceBetweenButtons)
        }
        positiveText?.also {
            DefaultPositiveButton(
                modifier = Modifier
                    .weight(1F)
                    .wrapContentHeight(),
                paddingValues = positivePaddingValues,
                positiveText = it,
                buttonTextStyle = positiveTextStyle,
                positiveButtonColor = positiveButtonColor,
                buttonPadding = buttonPadding,
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
    paddingValues: PaddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
    ),
    negativeText: String,
    buttonTextStyle: TextStyle? = null,
    buttonPadding: PaddingValues = getButtonPadding(),
    onClickNegative: () -> Unit,
) {
    val buttonModifier = modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .wrapContentHeight()

    if (buttonTextStyle != null) {
        DefaultTextButton(
            modifier = buttonModifier,
            text = negativeText,
            textColor = GrayScaleDarkGray1,
            style = buttonTextStyle,
            buttonColor = Color.Transparent,
            borderColor = GrayScaleMidGray2,
            buttonPadding = buttonPadding,
            onClick = onClickNegative,
        )
    } else {
        DefaultTextButton(
            modifier = buttonModifier,
            text = negativeText,
            textColor = GrayScaleMidGray3,
            buttonColor = Color.Transparent,
            borderColor = GrayScaleLightGray2,
            buttonPadding = buttonPadding,
            onClick = onClickNegative,
        )
    }
}

@Composable
internal fun DefaultPositiveButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
    ),
    positiveText: String,
    positiveButtonColor: Color? = BrandPrimary,
    buttonTextStyle: TextStyle? = null,
    buttonPadding: PaddingValues = getButtonPadding(),
    onClickPositive: () -> Unit,
) {
    val buttonModifier = modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .wrapContentHeight()

    if (buttonTextStyle != null) {
        DefaultTextButton(
            modifier = buttonModifier,
            enabled = enabled,
            text = positiveText,
            textColor = GrayScaleWhite,
            style = buttonTextStyle,
            buttonColor = positiveButtonColor ?: BrandPrimary,
            borderColor = positiveButtonColor ?: BrandPrimary,
            buttonPadding = buttonPadding,
            onClick = onClickPositive,
        )
    } else {
        DefaultTextButton(
            modifier = buttonModifier,
            enabled = enabled,
            text = positiveText,
            textColor = GrayScaleWhite,
            buttonColor = positiveButtonColor ?: BrandPrimary,
            borderColor = positiveButtonColor ?: BrandPrimary,
            buttonPadding = buttonPadding,
            onClick = onClickPositive,
        )
    }
}

@Composable
private fun DefaultOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
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
private fun DefaultTextButton(
    modifier: Modifier,
    enabled: Boolean = true,
    text: String,
    textColor: Color,
    buttonColor: Color,
    borderColor: Color,
    textSize: TextUnit = 12.sp,
    buttonPadding: PaddingValues,
    onClick: () -> Unit,
) {
    DefaultOutlinedButton(
        modifier = modifier,
        enabled = enabled,
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
private fun DefaultTextButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textColor: Color,
    style: TextStyle,
    buttonColor: Color,
    borderColor: Color,
    buttonPadding: PaddingValues,
    onClick: () -> Unit,
) {
    DefaultOutlinedButton(
        modifier = modifier,
        enabled = enabled,
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

@ComponentPreview
@Composable
private fun PreviewDefaultTwoButton() {
    DefaultTwoButton(
        positiveText = "positiveText",
        onClickPositive = {},
        negativeText = "negativeText",
        onClickNegative = {},
        spaceBetweenButtons = 9.dp,
    )
}
