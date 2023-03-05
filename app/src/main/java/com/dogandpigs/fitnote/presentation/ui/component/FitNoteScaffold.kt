package com.dogandpigs.fitnote.presentation.ui.component

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FitNoteScaffold(
    topBarTitle: String,
    topBarTitleStyle: TextStyle = LocalFitNoteTypography.current.titleLarge,
    onClickTopBarNavigationIcon: () -> Unit,
    topBarNavigationIconImageVector: ImageVector = Icons.Filled.ArrowBack,
    topBarActions: @Composable RowScope.() -> Unit = {},
    onClickBackButton: () -> Unit = {},
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
                        style = topBarTitleStyle,
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
        BackHandler {
            onClickBackButton()
        }

        content(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FitNoteScaffold(
    topBarTitle: String,
    topBarTitleStyle: TextStyle = LocalFitNoteTypography.current.titleExtraLarge,
    topBarActions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                    Text(
                        text = topBarTitle,
                        color = Color.Black,
                        style = topBarTitleStyle,
                    )
                },
                actions = topBarActions,
            )
        }
    ) {
        content(it)
    }
}
