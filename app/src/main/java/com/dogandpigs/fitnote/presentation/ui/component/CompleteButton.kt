package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary

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
        // TODO: 버튼 공통화
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
//                    .padding(paddingValues)
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