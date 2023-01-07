package com.dogandpigs.fitnote.presentation.lesson

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2

@Composable
internal fun LessonScreen(
    viewModel: LessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    Box {
        Lesson(
            uiState = viewModel.uiState,
            popBackStack = popBackStack,
            onClickAddLesson = navigateToAddLesson,
            navigateToSetting = navigateToSetting,
        )
    }
}

@Composable
private fun Lesson(
    uiState: LessonUiState,
    popBackStack: () -> Unit,
    onClickAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = "${uiState.userName} ${uiState.title}",
        topBarTitleFontSize = 20.sp,
        onClickTopBarNavigationIcon = popBackStack
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                LessonTabList()
            }

            AddLessonButton(
                onClickAddLesson
            )
        }
    }
}

data class Tab(
    val isSelected: Boolean,
    val name: String,
    val emptySubText: String = "",
    val list: List<Lesson>
) {
    data class Lesson(
        val name: String
    )
}

private val mockTabs = listOf(
    Tab(
        isSelected = true,
        name = "예정된 수업",
        emptySubText = "\n 수업을 추가해보세요!",
        list = listOf()
    ),
    Tab(
        isSelected = false,
        name = "완료한 수업",
        list = listOf()
    )
)

@Composable
private fun LessonTabList(
    tabs: List<Tab> = mockTabs,
) {
    val tabHeight = 42.dp

    Column {
        Row {
            for (tab in tabs) {
                LessonTab(
                    modifier = Modifier
                        .weight(1F)
                        .height(tabHeight),
                    title = tab.name,
                    isSelected = tab.isSelected,
                )
            }
        }
        val selectedTab = tabs.first {
            it.isSelected
        }

        if (selectedTab.list.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(123.dp),
                    painter = painterResource(id = R.drawable.image_lesson_empty),
                    contentDescription = ""
                )
                Text(
                    text = "${selectedTab.name}이 없습니다!${selectedTab.emptySubText}",
                    color = GrayScaleMidGray2,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
//                LessonList(tab.list)
        }
    }
}

@Composable
private fun LessonTab(
    modifier: Modifier,
    title: String,
    isSelected: Boolean,
) {
    val color = if (isSelected) {
        BrandPrimary
    } else {
        GrayScaleMidGray2
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1F),
            fontSize = 16.sp,
            color = color,
            textAlign = TextAlign.Center,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    if (isSelected) {
                        3.dp
                    } else {
                        1.dp
                    }
                )
                .background(color)
        )
    }
}

@Composable
private fun AddLessonButton(
    onClickAddLesson: () -> Unit,
) {
    val paddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 24.dp,
    )
    val buttonHeight = 52.dp

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            onClick = onClickAddLesson,
            modifier = Modifier
                .height(buttonHeight)
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary
            )
        ) {
            Text(
                text = "수업 추가",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
}

private val mockUiState = LessonUiState(
    title = "수업 목록",
    userName = "나초보 회원님",
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        Lesson(
            uiState = mockUiState,
            popBackStack = {},
            onClickAddLesson = {},
            navigateToSetting = {},
        )
    }
}
