package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberLessonListScreen(
    viewModel: MemberLessonListViewModel = hiltViewModel(),
    memberId: Int,
    popBackStack: () -> Unit,
    navigateToAddLesson: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
        viewModel.getIntendedLessonList()
    }
    
    MemberLessonList(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddLesson = navigateToAddLesson,
    )
}

@Composable
private fun MemberLessonList(
    uiState: MemberLessonListUiState,
    popBackStack: () -> Unit,
    onClickAddLesson: (Int) -> Unit,
) {
    var selectedTabType by remember { mutableStateOf(MemberLessonListUiState.Tab.TabType.SCHEDULED) }
    
    FitNoteScaffold(
        topBarTitle = "${
            String.format(
                stringResource(id = R.string.some_member), uiState.userName
            )
        } ${stringResource(id = R.string.lesson_list)}", onClickTopBarNavigationIcon = popBackStack
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                LessonTabList(
                    selectedTabType = selectedTabType,
                    scheduledLessonTab = uiState.scheduledLessonTab,
                    completedLessonTab = uiState.completedLessonTab,
                    onClickLessonTab = { tabType ->
                        selectedTabType = tabType
                    }
                )
            }
            
            if (selectedTabType == MemberLessonListUiState.Tab.TabType.SCHEDULED) {
                AddLessonButton(
                    uiState,
                    onClickAddLesson
                )
            }
        }
    }
}

@Composable
private fun LessonTabList(
    selectedTabType: MemberLessonListUiState.Tab.TabType,
    scheduledLessonTab: MemberLessonListUiState.Tab,
    completedLessonTab: MemberLessonListUiState.Tab,
    onClickLessonTab: (MemberLessonListUiState.Tab.TabType) -> Unit,
) {
    val tabHeight = 42.dp
    
    Column {
        Row {
            LessonTab(
                modifier = Modifier
                    .weight(1F)
                    .height(tabHeight),
                title = scheduledLessonTab.tabType.title,
                isSelected = selectedTabType == scheduledLessonTab.tabType,
                onClickLessonTab = {
                    onClickLessonTab(scheduledLessonTab.tabType)
                },
            )
            LessonTab(
                modifier = Modifier
                    .weight(1F)
                    .height(tabHeight),
                title = completedLessonTab.tabType.title,
                isSelected = selectedTabType == completedLessonTab.tabType,
                onClickLessonTab = {
                    onClickLessonTab(completedLessonTab.tabType)
                },
            )
        }
        
        val selectedTab = when (selectedTabType) {
            MemberLessonListUiState.Tab.TabType.SCHEDULED -> scheduledLessonTab
            MemberLessonListUiState.Tab.TabType.COMPLETED -> completedLessonTab
        }
        
        if (selectedTab.lessons.isEmpty()) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier.size(123.dp),
                    painter = painterResource(id = R.drawable.image_empty_lesson),
                    contentDescription = ""
                )
                HeightSpacer(height = 26.dp)
                Text(
                    text = "${selectedTab.tabType.title}이 없습니다!${selectedTab.tabType.message}",
                    color = GrayScaleMidGray2,
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
    onClickLessonTab: () -> Unit,
) {
    val color = if (isSelected) {
        BrandPrimary
    } else {
        GrayScaleLightGray2
    }
    
    val textColor = if (isSelected) {
        BrandPrimary
    } else {
        GrayScaleMidGray2
    }
    
    Column(
        modifier = modifier.clickable {
            onClickLessonTab()
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1F),
            fontSize = 16.sp,
            color = textColor,
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
private fun LessonList(
    lessons: List<MemberLessonListUiState.Tab.Lesson>
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
        modifier = Modifier.horizontalScroll(rememberScrollState())
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
    uiState: MemberLessonListUiState,
    onClickAddLesson: (Int) -> Unit,
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
            onClick = { onClickAddLesson(uiState.memberId) },
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

internal val previewScheduledLessonTab = MemberLessonListUiState.Tab(
    tabType = MemberLessonListUiState.Tab.TabType.SCHEDULED,
    lessons = listOf(
        MemberLessonListUiState.Tab.Lesson(
            dateString = "2022년 12월 25일", exercises = listOf(
                "벤치 프레스",
                "덤벨 프레스",
                "덤벨 플라이",
                "인클라인 벤치 프레스",
            )
        ),
        MemberLessonListUiState.Tab.Lesson(
            dateString = "2022년 12월 25일", exercises = listOf(
                "벤치 프레스",
                "덤벨 프레스",
                "덤벨 플라이",
                "인클라인 벤치 프레스",
            )
        ),
    ),
)
internal val previewCompletedLessonTab = MemberLessonListUiState.Tab(
    tabType = MemberLessonListUiState.Tab.TabType.COMPLETED,
    lessons = listOf(
        MemberLessonListUiState.Tab.Lesson(
            dateString = "2022년 12월 25일", exercises = listOf(
                "벤치 프레스",
                "덤벨 프레스",
                "덤벨 플라이",
                "인클라인 벤치 프레스",
            )
        ),
    ),
)

private val previewUiState = MemberLessonListUiState(
    userName = "나초보",
    scheduledLessonTab = previewScheduledLessonTab,
    completedLessonTab = previewCompletedLessonTab,
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        MemberLessonList(
            uiState = previewUiState,
            popBackStack = {},
            onClickAddLesson = {},
        )
    }
}
