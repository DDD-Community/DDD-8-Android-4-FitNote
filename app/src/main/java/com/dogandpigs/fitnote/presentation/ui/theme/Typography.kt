package com.dogandpigs.fitnote.presentation.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.R

private val pretendard = FontFamily(
    Font(R.font.pretendard_black, FontWeight.Black),
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_extra_bold, FontWeight.ExtraBold),
    Font(R.font.pretendard_extra_light, FontWeight.ExtraLight),
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semi_bold, FontWeight.SemiBold),
    Font(R.font.pretendard_thin, FontWeight.Thin),
)

class FitNoteDesignSystemTypography constructor(
    val titleExtraLarge: TextStyle,
    val titleLarge: TextStyle,
    val titleDefault: TextStyle,
    val titleSmall: TextStyle,
    val textExtraLarge: TextStyle,
    val textLarge: TextStyle,
    val textDefault: TextStyle,
    val textSmall: TextStyle,
    val buttonDefault: TextStyle,
    val buttonMedium: TextStyle,
    val buttonSmall: TextStyle,
    val underlineDefault: TextStyle,
    val underlineSmall: TextStyle,
) {
    constructor(
        defaultFontFamily: FontFamily = pretendard,
        titleExtraLarge: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 42.sp,
        ),
        titleLarge: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
        ),
        titleDefault: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 26.sp,
        ),
        titleSmall: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 19.sp,
        ),
        textExtraLarge: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 42.sp,
        ),
        textLarge: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 32.sp,
        ),
        textDefault: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 26.sp,
        ),
        textSmall: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 19.sp,
        ),
        buttonDefault: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 19.sp,
        ),
        buttonMedium: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 19.sp,
        ),
        buttonSmall: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 14.sp,
        ),
        underlineDefault: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 26.sp,
            textDecoration = TextDecoration.Underline,
        ),
        underlineSmall: TextStyle = TextStyle(
            fontFamily = defaultFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 19.sp,
            textDecoration = TextDecoration.Underline,
        ),
    ) : this(
        titleExtraLarge = titleExtraLarge,
        titleLarge = titleLarge,
        titleDefault = titleDefault,
        titleSmall = titleSmall,
        textExtraLarge = textExtraLarge,
        textLarge = textLarge,
        textDefault = textDefault,
        textSmall = textSmall,
        buttonDefault = buttonDefault,
        buttonMedium = buttonMedium,
        buttonSmall = buttonSmall,
        underlineDefault = underlineDefault,
        underlineSmall = underlineSmall,
    )
}

val LocalFitNoteTypography = staticCompositionLocalOf { FitNoteDesignSystemTypography() }

@Preview
@Composable
private fun PreviewTypography() {
    FitNoteTheme {
        Column {
            Text(
                text = "titleExtraLarge",
                style = LocalFitNoteTypography.current.titleExtraLarge,
            )
            Text(
                text = "titleLarge",
                style = LocalFitNoteTypography.current.titleLarge,
            )
            Text(
                text = "titleDefault",
                style = LocalFitNoteTypography.current.titleDefault,
            )
            Text(
                text = "titleSmall",
                style = LocalFitNoteTypography.current.titleSmall,
            )
            Text(
                text = "textExtraLarge",
                style = LocalFitNoteTypography.current.textExtraLarge,
            )
            Text(
                text = "textLarge",
                style = LocalFitNoteTypography.current.textLarge,
            )
            Text(
                text = "textDefault",
                style = LocalFitNoteTypography.current.textDefault,
            )
            Text(
                text = "textSmall",
                style = LocalFitNoteTypography.current.textSmall,
            )
            Text(
                text = "buttonDefault",
                style = LocalFitNoteTypography.current.buttonDefault,
            )
            Text(
                text = "buttonMedium",
                style = LocalFitNoteTypography.current.buttonMedium,
            )
            Text(
                text = "buttonSmall",
                style = LocalFitNoteTypography.current.buttonSmall,
            )
            Text(
                text = "underlineDefault",
                style = LocalFitNoteTypography.current.underlineDefault,
            )
            Text(
                text = "underlineSmall",
                style = LocalFitNoteTypography.current.underlineSmall,
            )
        }
    }
}
