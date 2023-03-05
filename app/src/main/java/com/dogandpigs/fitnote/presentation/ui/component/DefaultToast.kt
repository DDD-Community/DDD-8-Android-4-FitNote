package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import kotlinx.coroutines.delay

@Composable
internal fun DefaultToast(
    visible: Boolean,
    text: String,
    textColor: Color = GrayScaleWhite,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
        vertical = LocalFitNoteSpacing.current.spacing5,
    ),
    backgroundColor: Color = GrayScaleDarkGray1,
    timeMillis: Long,
    onFinish: () -> Unit,
) {
    LaunchedEffect(Unit) {
        delay(timeMillis)
        onFinish()
    }

    if (visible) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(5.dp))
                    .background(backgroundColor)
                    .padding(vertical = LocalFitNoteSpacing.current.spacing4),
                text = text,
                color = textColor,
                style = LocalFitNoteTypography.current.textDefault,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun PreviewDefaultToast() {
    val visible = remember { mutableStateOf(false) }

    DefaultToast(
        visible = visible.value,
        text = "회원 등록이 완료되었습니다!",
        timeMillis = 5_000L,
        onFinish = { visible.value = false },
    )
}
