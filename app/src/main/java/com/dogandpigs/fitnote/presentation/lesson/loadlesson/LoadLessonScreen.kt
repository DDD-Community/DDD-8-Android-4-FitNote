package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoadLessonScreen(
    viewModel: LoadLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadLesson(state)
}

@Composable
internal fun LoadLesson(uiState: LoadLessonUiState) {
    val navController = rememberNavController()

    FitNoteScaffold(topBarTitle = stringResource(id = R.string.title_load_lesson),
        topBarTitleFontSize = 16.sp,
        onClickTopBarNavigationIcon = { navController.navigateUp() },
        topBarNavigationIconImageVector = Icons.Filled.Close,
        topBarActions = {
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(id = R.string.btn_load),
                    color = colorResource(id = R.color.brand_primary)
                )
            }
        }) {

    }
}

private val mockUiState = LoadLessonUiState(
    title = "mock AddLesson title"
)

@FigmaPreview
@Composable
internal fun PreviewLoadLesson() {
    FitNoteTheme {
        LoadLesson(mockUiState)
    }
}