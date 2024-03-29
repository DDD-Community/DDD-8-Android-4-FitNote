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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDialog
import com.dogandpigs.fitnote.presentation.ui.component.DefaultPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTwoButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.util.formatYMD

@Composable
internal fun MemberLessonListScreen(
    viewModel: MemberLessonListViewModel = hiltViewModel(),
    memberId: Int,
    popBackStack: () -> Unit,
    navigateToAddLesson: (Int) -> Unit,
    navigateToMemberLesson: (memberId: Int, lessonDate: Int) -> Unit,
    navigateToShare: (memberId: Int, lessonDate: Int) -> Unit,
    navigateToEdit: (memberId: Int, lessonDate: Int) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
    }

    MemberLessonList(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddLesson = navigateToAddLesson,
        onClickStartLesson = { lessonDate ->
            navigateToMemberLesson(memberId, lessonDate)
        },
        onClickShare = { lessonDate ->
            navigateToShare(memberId, lessonDate)
        },
        onEditButtonClick = { lessonDate ->
            navigateToEdit(memberId, lessonDate)
        },
        onDeleteIconClick = viewModel::setSelectedLessonDate,
        onItemDelete = viewModel::deleteLesson,
    )
}

@Composable
private fun MemberLessonList(
    uiState: MemberLessonListUiState,
    popBackStack: () -> Unit,
    onClickAddLesson: (Int) -> Unit,
    onClickStartLesson: (Int) -> Unit,
    onClickShare: (lessonId: Int) -> Unit,
    onEditButtonClick: (lessonId: Int) -> Unit,
    onDeleteIconClick: (lessonDate: String) -> Unit,
    onItemDelete: () -> Unit,
) {
    val visibleLessonDeleteDialog = remember { mutableStateOf(false) }
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
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                LessonTabList(
                    selectedTabType = selectedTabType,
                    scheduledLessonTab = uiState.scheduledLessonTab,
                    completedLessonTab = uiState.completedLessonTab,
                    onClickLessonTab = { tabType ->
                        selectedTabType = tabType
                    },
                    onEditButtonClick = onEditButtonClick,
                    onClickLessonStart = onClickStartLesson,
                    onClickShare = onClickShare,
                    onDeleteIconClick = { lessonDate ->
                        visibleLessonDeleteDialog.value = true
                        onDeleteIconClick(lessonDate)
                    },
                )
            }

            if (selectedTabType == MemberLessonListUiState.Tab.TabType.SCHEDULED) {
                AddLessonButton(
                    uiState,
                    onClickAddLesson
                )
            }

            LessonDeleteDialog(
                visible = visibleLessonDeleteDialog.value,
                onClickPositive = {
                    visibleLessonDeleteDialog.value = false
                    onItemDelete()
                },
                onClickNegative = {
                    visibleLessonDeleteDialog.value = false
                },
            )
        }
    }
}

@Composable
private fun LessonTabList(
    selectedTabType: MemberLessonListUiState.Tab.TabType,
    scheduledLessonTab: MemberLessonListUiState.Tab,
    completedLessonTab: MemberLessonListUiState.Tab,
    onClickLessonTab: (MemberLessonListUiState.Tab.TabType) -> Unit,
    onEditButtonClick: (lessonId: Int) -> Unit,
    onClickLessonStart: (Int) -> Unit,
    onClickShare: (lessonId: Int) -> Unit,
    onDeleteIconClick: (lessonDate: String) -> Unit,
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
                LessonList(
                    selectedTabType = selectedTabType,
                    lessons = selectedTab.lessons,
                    onEditButtonClick = onEditButtonClick,
                    onClickLessonStart = onClickLessonStart,
                    onClickShare = onClickShare,
                    onDeleteIconClick = onDeleteIconClick,
                )

                HeightSpacer(height = LocalFitNoteSpacing.current.spacing10)
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
    selectedTabType: MemberLessonListUiState.Tab.TabType,
    lessons: List<MemberLessonListUiState.Tab.Lesson>,
    onEditButtonClick: (lessonId: Int) -> Unit,
    onClickLessonStart: (Int) -> Unit,
    onClickShare: (lessonId: Int) -> Unit,
    onDeleteIconClick: (lessonDate: String) -> Unit,
) {
    for (lesson in lessons) {
        HeightSpacer(height = 24.dp)
        LessonItem(
            selectedTabType = selectedTabType,
            lesson = lesson,
            onEditButtonClick = onEditButtonClick,
            onClickLessonStart = onClickLessonStart,
            onClickShare = onClickShare,
            onDeleteIconClick = onDeleteIconClick,
        )
    }
}

@Composable
private fun LessonItem(
    selectedTabType: MemberLessonListUiState.Tab.TabType,
    lesson: MemberLessonListUiState.Tab.Lesson,
    onEditButtonClick: (lessonId: Int) -> Unit,
    onClickLessonStart: (Int) -> Unit,
    onClickShare: (lessonId: Int) -> Unit,
    onDeleteIconClick: (lessonDate: String) -> Unit,
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
                modifier = Modifier.weight(1f),
                text = lesson.dateString.formatYMD(),
                fontSize = 16.sp,
            )

            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = stringResource(id = R.string.delete),
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onDeleteIconClick(lesson.dateString)
                    },
            )
        }
        HeightSpacer(height = 24.dp)
        ExerciseRow(lesson.exercises)
        HeightSpacer(height = 16.dp)
        Divider(color = GrayScaleLightGray1)
        HeightSpacer(height = 16.dp)
        when (selectedTabType) {
            MemberLessonListUiState.Tab.TabType.SCHEDULED -> {
                LessonItemButtons(
                    onClickLessonEdit = {
                        onEditButtonClick(lesson.dateString.toInt())
                    },
                    onClickLessonStart = {
                        onClickLessonStart(lesson.dateString.toInt())
                    },
                )
            }
            MemberLessonListUiState.Tab.TabType.COMPLETED -> {
                DefaultPositiveButton(
                    positiveText = "공유하기",
                    onClickPositive = {
                        onClickShare(lesson.dateString.toInt())
                    }
                )
            }
        }
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
private fun LessonItemButtons(
    onClickLessonEdit: () -> Unit,
    onClickLessonStart: () -> Unit,
) {
    Row {
        DefaultTwoButton(
            positiveText = "수업 시작",
            onClickPositive = onClickLessonStart,
            negativeText = "편집",
            onClickNegative = onClickLessonEdit,
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
                containerColor = BrandPrimary,
            ),
            border = null,
        ) {
            Text(
                text = stringResource(id = R.string.lesson_add),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun LessonDeleteDialog(
    visible: Boolean,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit,
) {
    DefaultDialog(
        visible = visible,
        onDismissRequest = onClickNegative,
        message = stringResource(id = R.string.lesson_delete_message),
        positiveText = stringResource(id = R.string.confirm),
        onPositiveClick = onClickPositive,
        positiveButtonColor = Alert,
        negativeText = stringResource(id = R.string.cancel),
        onNegativeClick = onClickNegative,
    )
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
            onClickStartLesson = {},
            onClickShare = {},
            onEditButtonClick = {},
            onDeleteIconClick = {},
            onItemDelete = {},
        )
    }
}
