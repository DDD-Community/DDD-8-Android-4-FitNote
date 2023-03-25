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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.base.MainEvent
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import kotlinx.coroutines.delay

@Composable
internal fun DefaultToast(
    mainEvent: MainEvent,
) {
    val customToastEvent = (mainEvent as? MainEvent.CustomToast) ?: return
    val visible = remember { mutableStateOf(true) }
    val timeMillis = 3_000L
    val paddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
        vertical = LocalFitNoteSpacing.current.spacing5 + mainEvent.bottomPadding,
    )

    LaunchedEffect(visible.value) {
        if (visible.value) {
            delay(timeMillis)
            visible.value = false
        }
    }

    if (visible.value) {
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
                    .background(GrayScaleDarkGray1)
                    .padding(vertical = LocalFitNoteSpacing.current.spacing4),
                text = customToastEvent.message,
                color = GrayScaleWhite,
                style = LocalFitNoteTypography.current.textDefault,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun PreviewDefaultToast() {
    DefaultToast(
        mainEvent = MainEvent.CustomToast(
            message = "회원 등록이 완료되었습니다!",
        )
    )
}
