package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun WidthSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
internal fun HeightSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
internal fun DefaultSpacer(
    width: Dp = 0.dp,
    height: Dp = 0.dp,
) {
    Spacer(
        modifier = Modifier
            .width(width)
            .height(height)
    )
}

internal fun Modifier.defaultBorder(): Modifier = composed {
    this.border(
        width = 1.dp,
        color = colorResource(id = R.color.border_gray),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
internal fun DefaultText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    style: TextStyle,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = style,
    )
}

internal val passwordVisualTransformation: VisualTransformation =
    PasswordVisualTransformation(
        mask = '\u002A'
    )

@Composable
internal fun CompleteButton(
    text: String,
    isReadyComplete: Boolean = true,
    onClick: () -> Unit,
) {
    val buttonHeight = 52.dp
    val paddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 24.dp,
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
                .background(Color.Transparent),
            enabled = isReadyComplete,
            onClick = onClick,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isReadyComplete) {
                    BrandPrimary
                } else {
                    GrayScaleMidGray1
                }
            ),
            border = null,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = LocalFitNoteTypography.current.buttonDefault,
                color = GrayScaleWhite,
            )
        }
    }
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

