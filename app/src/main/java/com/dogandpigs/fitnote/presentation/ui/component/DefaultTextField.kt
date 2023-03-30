package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    supportingText: @Composable (() -> Unit)? = null,
) {
    val paddingValues = PaddingValues(
        horizontal = 20.dp,
        vertical = 10.dp,
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .background(Color.Transparent),
        label = label,
        placeholder = placeholder,
        suffix = suffix,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            errorCursorColor = Alert,
            errorIndicatorColor = Alert,
            focusedIndicatorColor = BrandPrimary,
        ),
    )
}

@Composable
internal fun DefaultTextFieldLabel(
    text: String,
) {
    Text(
        text = text,
        color = GrayScaleMidGray2,
    )
}

@Composable
internal fun DefaultTextFieldPlaceholder(
    text: String,
) {
    Text(
        text = text,
        color = GrayScaleDarkGray2,
        style = LocalFitNoteTypography.current.textDefault,
    )
}


@Composable
internal fun DefaultTextFieldSuffix(
    text: String,
) {
    Text(
        text = text,
        color = GrayScaleDarkGray2,
        style = LocalFitNoteTypography.current.textDefault,
    )
}
