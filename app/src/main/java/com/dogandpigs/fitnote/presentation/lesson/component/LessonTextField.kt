package com.dogandpigs.fitnote.presentation.lesson.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LessonTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = TextFieldDefaults.filledShape,
    placeholder: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
) {
    BasicTextField(
        value = text,
        onValueChange = { textValue ->
            onValueChange(textValue)
        },
        modifier = modifier,
        enabled = enabled,
        textStyle = LocalFitNoteTypography.current.buttonMedium.copy(
            color = GrayScaleMidGray3,
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        maxLines = 1,
        cursorBrush = SolidColor(BrandPrimary),
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                placeholder = placeholder,
                suffix = suffix,
                shape = shape,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = 6.dp
                ),
                container = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(5.dp))
                            .background(GrayScaleLightGray1)
                            .padding(
                                start = LocalFitNoteSpacing.current.spacing4,
                                top = 6.dp,
                                bottom = 6.dp,
                            )
                    ) {}
                }
            )
        }
    )
}

@Composable
internal fun LessonSetTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
) {
    LessonSuffixTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        suffix = "세트",
    )
}

@Composable
internal fun LessonWeightTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
) {
    LessonSuffixTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        suffix = "kg",
    )
}

@Composable
internal fun LessonCountTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
) {
    LessonSuffixTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        suffix = "회",
    )
}

@Composable
private fun LessonSuffixTextField(
    modifier: Modifier,
    text: String,
    onValueChange: (String) -> Unit = {},
    enabled: Boolean = true,
    suffix: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    LessonTextField(
        modifier = modifier,
        text = text,
        onValueChange = onValueChange,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        suffix = {
            DefaultText(
                text = suffix,
                color = GrayScaleMidGray3,
                style = LocalFitNoteTypography.current.buttonMedium,
            )
        },
    )
}

@FigmaPreview
@Composable
private fun PreviewLessonTextField() {
    LessonTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        text = "운동명",
        onValueChange = {},
    )
}
