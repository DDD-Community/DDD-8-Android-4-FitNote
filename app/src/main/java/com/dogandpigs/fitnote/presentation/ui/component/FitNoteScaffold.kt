package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FitNoteScaffold(
    topBarTitle: String,
    topBarTitleFontSize: TextUnit = 14.sp,
    onClickTopBarNavigationIcon: () -> Unit,
    topBarNavigationIconImageVector: ImageVector = Icons.Filled.ArrowBack,
    topBarActions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = topBarTitle,
                        color = Color.Black,
                        fontSize = topBarTitleFontSize
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onClickTopBarNavigationIcon) {
                        Icon(
                            imageVector = topBarNavigationIconImageVector,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = topBarActions,
            )
        }
    ) {
        content(it)
    }
}
