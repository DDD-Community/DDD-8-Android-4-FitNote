package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTwoButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberLessonListScreen(
    viewModel: MemberLessonListViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MemberLessonList(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddLesson = navigateToAddLesson,
        navigateToSetting = navigateToSetting,
    )
}

@Composable
private fun MemberLessonList(
    uiState: MemberLessonListUiState,
    popBackStack: () -> Unit,
    onClickAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = "${
            String.format(
                stringResource(id = R.string.some_member),
                uiState.userName
            )
        } ${stringResource(id = R.string.lesson_list)}",
        onClickTopBarNavigationIcon = popBackStack
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                LessonTabList(
                    uiState.tabs
                )
            }

            AddLessonButton(
                onClickAddLesson
            )
        }
    }
}

@Composable
private fun LessonTabList(
    tabs: List<MemberLessonListUiState.Tab>,
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

        Column(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (selectedTab.lessons.isEmpty()) {
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
            } else {
                LessonList(selectedTab.lessons)
            }
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

@Preview
@Composable
private fun LessonList(
    lessons: List<MemberLessonListUiState.Tab.Lesson> = previewUiState.tabs.first().lessons
) {
    for (lesson in lessons) {
        HeightSpacer(height = 24.dp)
        LessonItem(lesson)
    }
}

@Composable
private fun LessonItem(
    lesson: MemberLessonListUiState.Tab.Lesson
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultBorder()
            .padding(
                horizontal = 16.dp
            )
    ) {
        HeightSpacer(height = 16.dp)
        Row {
            Text(
                text = "2022년 12월 25일",
                fontSize = 16.sp,
            )
        }
        HeightSpacer(height = 24.dp)
        ExerciseRow(lesson.exercises)
        HeightSpacer(height = 16.dp)
        Divider(color = GrayScaleLightGray1)
        HeightSpacer(height = 16.dp)
        LessonItemButtons()
        HeightSpacer(height = 13.dp)
    }
}

@Composable
private fun ExerciseRow(
    exercises: List<String>,
) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        for (exercise in exercises) {
            ExerciseItem(exercise)
            WidthSpacer(width = 16.dp)
        }
    }
}

@Composable
private fun ExerciseItem(
    exercise: String,
) {
    val paddingValues = PaddingValues(
        horizontal = 10.dp,
        vertical = 6.dp,
    )

    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(GrayScaleLightGray1)
            .padding(paddingValues),
        text = exercise,
        color = GrayScaleMidGray3,
        fontSize = 16.sp,
    )
}

@Composable
private fun LessonItemButtons() {
    Row {
        DefaultTwoButton(
            positiveText = "수업 시작",
            onClickPositive = {},
            negativeText = "편집",
            onClickNegative = {},
            spaceBetweenButtons = 9.dp,
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
                text = stringResource(id = R.string.add_lesson),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
}

internal val previewTabs = listOf(
    MemberLessonListUiState.Tab(
        isSelected = true,
        name = "예정된 수업",
        emptySubText = "\n 수업을 추가해보세요!",
        lessons = listOf(
            MemberLessonListUiState.Tab.Lesson(
                dateString = "2022년 12월 25일",
                exercises = listOf(
                    "벤치 프레스",
                    "덤벨 프레스",
                    "덤벨 플라이",
                    "인클라인 벤치 프레스",
                )
            ),
            MemberLessonListUiState.Tab.Lesson(
                dateString = "2022년 12월 25일",
                exercises = listOf(
                    "벤치 프레스",
                    "덤벨 프레스",
                    "덤벨 플라이",
                    "인클라인 벤치 프레스",
                )
            ),
        ),
    ),
    MemberLessonListUiState.Tab(
        isSelected = false,
        name = "완료한 수업",
        emptySubText = "",
        lessons = listOf(
            MemberLessonListUiState.Tab.Lesson(
                dateString = "2022년 12월 25일",
                exercises = listOf(
                    "벤치 프레스",
                    "덤벨 프레스",
                    "덤벨 플라이",
                    "인클라인 벤치 프레스",
                )
            ),
        ),
    ),
)

private val previewUiState = MemberLessonListUiState(
    userName = "나초보",
    tabs = previewTabs,
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        MemberLessonList(
            uiState = previewUiState,
            popBackStack = {},
            onClickAddLesson = {},
            navigateToSetting = {},
        )
    }
}
