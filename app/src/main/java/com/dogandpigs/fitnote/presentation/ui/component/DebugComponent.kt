package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary

// TODO Dialog
@Composable
internal fun DebugMenu(
    vararg navigatePairs: Pair<String, (() -> Unit)>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (navigatePair in navigatePairs) {
            DebugButton(
                text = navigatePair.first,
                onClick = navigatePair.second,
            )
            HeightSpacer(height = 20.dp)
        }
    }
}

@Composable
private fun DebugButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(300.dp)
            .padding(0.dp, 5.dp)
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = SubPrimary,
            contentColor = Color.White,
        ),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        Text(text = text)
    }
}
