package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

internal class SuffixVisualTransformation(
    private val suffix: String
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (text.equals(0)) {
                    0
                } else {
                    when (offset) {
                        0, 1, 2 -> offset
                        else -> text.length
                    }
                }
            }
        }

        return TransformedText(
            text = AnnotatedString("$text $suffix"),
            offsetMapping = offsetMapping,
        )
    }
}
