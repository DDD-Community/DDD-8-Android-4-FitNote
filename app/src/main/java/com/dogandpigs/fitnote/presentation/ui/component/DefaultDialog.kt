package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun DefaultDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    title: String = stringResource(id = R.string.info),
    message: String,

    positiveText: String? = null,
    onPositiveClick: (() -> Unit)? = null,
    positiveButtonColor: Color? = null,

    negativeText: String? = null,
    onNegativeClick: (() -> Unit)? = null,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
        ) {
            DefaultDialog(
                title = title,
                message = message,
            ) {
                DefaultTwoButton(
                    positiveText = positiveText,
                    onClickPositive = onPositiveClick,
                    positiveTextStyle = LocalFitNoteTypography.current.buttonDefault.copy(
                        color = GrayScaleWhite
                    ),
                    negativeText = negativeText,
                    onClickNegative = onNegativeClick,
                    negativeTextStyle = LocalFitNoteTypography.current.buttonDefault.copy(
                        color = GrayScaleDarkGray1
                    ),
                    spaceBetweenButtons = 16.dp,
                    positiveButtonColor = positiveButtonColor,
                )
            }
        }
    }
}

@Composable
private fun DefaultDialog(
    title: String,
    message: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = 16.dp,
            )
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(color = GrayScaleWhite)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = LocalFitNoteTypography.current.titleLarge,
            color = GrayScaleDarkGray2,
            textAlign = TextAlign.Center,
        )
        HeightSpacer(height = 16.dp)
        Text(
            text = message,
            style = LocalFitNoteTypography.current.textDefault,
            color = GrayScaleDarkGray2,
            textAlign = TextAlign.Center,
        )
        HeightSpacer(height = 32.dp)

        content()
    }
}

private const val previewPositiveText: String = "확인"
private const val previewNegativeText: String = "취소"
private const val previewTitle: String = "안내"
private const val previewMessage: String = "인증번호가 일치하지 않습니다.\n재인증해주세요."

@FigmaPreview
@Composable
private fun PreviewDefaultPositiveDialog() {
    DefaultDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onPositiveClick = {},
        title = previewTitle,
        message = previewMessage,
    )
}

@FigmaPreview
@Composable
private fun PreviewDefaultBlueDialog() {
    DefaultDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onPositiveClick = {},
        negativeText = previewNegativeText,
        onNegativeClick = {},
        title = previewTitle,
        message = previewMessage,
    )
}

@FigmaPreview
@Composable
private fun PreviewDefaultRedDialog() {
    DefaultDialog(
        visible = true,
        onDismissRequest = {},
        positiveText = previewPositiveText,
        onPositiveClick = {},
        positiveButtonColor = Alert,
        negativeText = previewNegativeText,
        onNegativeClick = {},
        title = previewTitle,
        message = previewMessage,
    )
}
