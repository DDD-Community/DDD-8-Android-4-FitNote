package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary

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

