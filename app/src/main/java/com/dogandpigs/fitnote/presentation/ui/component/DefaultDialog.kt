package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite

@Composable
internal fun DefaultPositiveDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    title: String,
    message: String,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            DefaultDialog(
                positiveText = positiveText,
                onClickPositive = onClickPositive,
                title = title,
                message = message,
            )
        }
    }
}

@Composable
internal fun DefaultBlueDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    negativeText: String? = null,
    onClickNegative: (() -> Unit)? = null,
    title: String,
    message: String,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            DefaultDialog(
                positiveText = positiveText,
                onClickPositive = onClickPositive,
                negativeText = negativeText,
                onClickNegative = onClickNegative,
                title = title,
                message = message,
            )
        }
    }
}

@Composable
internal fun DefaultRedDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    negativeText: String? = null,
    onClickNegative: (() -> Unit)? = null,
    title: String,
    message: String,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest
        ) {
            DefaultDialog(
                positiveText = positiveText,
                onClickPositive = onClickPositive,
                negativeText = negativeText,
                onClickNegative = onClickNegative,
                title = title,
                message = message,
                positiveButtonColor = Alert,
            )
        }
    }
}

@Composable
private fun DefaultDialog(
    positiveText: String? = null,
    onClickPositive: (() -> Unit)? = null,
    negativeText: String? = null,
    onClickNegative: (() -> Unit)? = null,
    title: String,
    message: String,
    positiveButtonColor: Color? = null,
) {
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(color = GrayScaleWhite)
                .padding(16.dp),
        ) {
            Column {
                DialogText(
                    text = title,
                    fontSize = 20.sp,
                )
                HeightSpacer(height = 16.dp)
                DialogText(
                    text = message,
                    fontSize = 16.sp,
                )
            }
            HeightSpacer(height = 32.dp)
            DefaultTwoButton(
                positiveText = positiveText,
                onClickPositive = onClickPositive,
                negativeText = negativeText,
                onClickNegative = onClickNegative,
                spaceBetweenButtons = 16.dp,
                positiveButtonColor = positiveButtonColor,
            )
        }
    }
}

@Composable
private fun DialogText(
    text: String,
    fontSize: TextUnit,
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = GrayScaleDarkGray2,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
    )
}

private const val previewPositiveText: String = "확인"
private const val previewNegativeText: String = "취소"
private const val previewTitle: String = "안내"
private const val previewMessage: String = "인증번호가 일치하지 않습니다.\n재인증해주세요."

@FigmaPreview
@Composable
private fun PreviewDefaultPositiveDialog() {
    DefaultPositiveDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onClickPositive = {},
        title = previewTitle,
        message = previewMessage,
    )
}

@FigmaPreview
@Composable
private fun PreviewDefaultBlueDialog() {
    DefaultBlueDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onClickPositive = {},
        negativeText = previewNegativeText,
        onClickNegative = {},
        title = previewTitle,
        message = previewMessage,
    )
}

@FigmaPreview
@Composable
private fun PreviewDefaultRedDialog() {
    DefaultRedDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onClickPositive = {},
        negativeText = previewNegativeText,
        onClickNegative = {},
        title = previewTitle,
        message = previewMessage,
    )
}
