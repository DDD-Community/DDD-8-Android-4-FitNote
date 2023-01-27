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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun WidthSpacer(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}

@Composable
internal fun HeightSpacer(height: Dp) {
    Spacer(modifier = Modifier.height(height))
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
    text: String,
    color: Color,
    style: TextStyle,
) {
    Text(
        text = text,
        color = color,
        style = style,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DefaultTextField(
    isError: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    placeholderText: String,
    isPassword: Boolean = false,
) {
    TextField(
        isError = isError,
        label = {
            Text(
                text = labelText,
                color = GrayScaleMidGray2,
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            errorCursorColor = Alert,
            errorIndicatorColor = Alert,
            focusedIndicatorColor = BrandPrimary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp)
            .background(Color.Transparent),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                color = GrayScaleDarkGray2,
                style = LocalFitNoteTypography.current.textDefault,
            )
        },
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation(
                mask = '\u002A'
            )
        } else {
            VisualTransformation.None
        }
    )
}

@Composable
internal fun CloseButton(onClick: () -> Unit) {
    IconButton(
        modifier = Modifier.wrapContentWidth(),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Filled.Close, contentDescription = "Back"
        )
    }
}


@Composable
internal fun CompleteButton(text: String, onClick: () -> Unit) {
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
            onClick = onClick,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary
            )
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White
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

