package com.dogandpigs.fitnote.presentation.share

import android.content.Intent
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@Composable
internal fun ShareScreen(
    memberId: Int,
    lessonDate: Int,
) {
    Share(
        memberId = memberId,
        lessonDate = lessonDate,
    )
}

@Composable
private fun Share(
    memberId: Int,
    lessonDate: Int,
) {
    val context = LocalContext.current

    Box {
        val url = "http://52.79.105.64/hypeboy/share/$memberId/$lessonDate"

        MyContent(
            url = url,
        )

        DefaultBottomLargePositiveButton(
            positiveText = "공유하기",
            onClickPositive = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, url)
                }
                context.startActivity(Intent.createChooser(intent, url))
            },
        ) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
        }
    }
}

@Composable
fun MyContent(
    url: String,
) {
    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }, update = {
            it.loadUrl(url)
        }
    )
}

@ComponentPreview
@Composable
private fun PreviewShareScreen() {
    Share(
        memberId = 0,
        lessonDate = 0,
    )
}
