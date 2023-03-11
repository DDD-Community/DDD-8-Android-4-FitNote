package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.addlesson.ExpandableCard
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import com.dogandpigs.fitnote.presentation.ui.DefaultValue
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultSpacer
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary

@Composable
internal fun LoadLessonScreen(
    viewModel: LoadLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    viewModel.initialize()
    LoadLesson(
        lessonState = uiState,
        popBackStack = popBackStack,
        onLoadButtonClick = {},
    )
}

@Composable
private fun LoadLesson(
    lessonState: LoadLessonState,
    popBackStack: () -> Unit,
    onLoadButtonClick: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.load),
        onClickTopBarNavigationIcon = { popBackStack() },
        topBarNavigationIconImageVector = Icons.Filled.ArrowBack,
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(Color.White),
        ) {
            LoadLessonContent(
                lessonState = lessonState,
            )

            BottomPositiveButton(
                text = stringResource(id = R.string.load),
                onClick = onLoadButtonClick,
            )
        }
    }
}

@Composable
private fun LoadLessonContent(
    lessonState: LoadLessonState,
) {
    val selectedMemberIndex = remember { mutableStateOf(DefaultValue.ITEM_INDEX_NOT_SELECTED) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing5)
        RowMemberNameList(
            memberList = lessonState.memberList,
            selectedMemberIndex = selectedMemberIndex.value,
            onMemberNameClick = {
                selectedMemberIndex.value = it
            },
        )
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing5)
        RoutineList(lessonState)
    }
}

@Composable
private fun RowMemberNameList(
    memberList: List<LoadLessonState.Member>,
    selectedMemberIndex: Int,
    onMemberNameClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(scrollState)
    ) {
        memberList.forEachIndexed { index, member ->
            MemberNameTag(
                name = member.name,
                isSelected = index == selectedMemberIndex,
                onMemberNameClick = {
                    onMemberNameClick(index)
                }
            )
            DefaultSpacer(width = LocalFitNoteSpacing.current.spacing3)
        }
    }
}

@Composable
private fun RowExerciseList(
    exerciseList: List<String>,
    borderRadius: Dp,
    paddingValue: PaddingValues
) {
    val scrollState = rememberScrollState()
    Row(
        Modifier.horizontalScroll(scrollState)
    ) {
        exerciseList.forEach { name ->
        }
    }
}

@Composable
private fun MemberNameTag(
    name: String,
    isSelected: Boolean,
    onMemberNameClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onMemberNameClick,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) {
                SubPrimary
            } else {
                GrayScaleLightGray1
            },
            containerColor = if (isSelected) {
                BrandPrimary
            } else {
                GrayScaleLightGray2
            }
        ),
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 6.dp,
        ),
    ) {
        DefaultText(
            text = name,
            color = if (isSelected) {
                BrandPrimary
            } else {
                GrayScaleMidGray3
            },
            style = LocalFitNoteTypography.current.buttonSmall,
        )
    }
}

@Composable
private fun RoutineList(lessonsState: LoadLessonState) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
//            .padding(paddingValues)
            .background(color = Color.White)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lessonsState.exerciseList.forEach {
            ExerciseList(it)
            HeightSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun ExerciseList(description: LessonInfo) {
    val selectedValue = remember { mutableStateOf("") }
    val label = "Item"
    val list = mutableListOf<Routine>().apply {
        add(Routine())
        add(Routine())
        add(Routine())
        add(Routine())
        add(Routine())
    }
    Column(
        modifier = Modifier
            .defaultBorder()
            .padding(16.dp, 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description.lessonsDate.toString(),
                color = GrayScaleMidGray3,
                style = LocalFitNoteTypography.current.buttonMedium
            )
            Spacer(modifier = Modifier.weight(2f))
            RadioButton(
                selected = selectedValue.value == label,
                onClick = { selectedValue.value = label }
            )
        }
        RowExerciseList(description.lessonsName, 50.dp, PaddingValues(10.dp, 6.dp))
        ExpandableCard(
            header = stringResource(id = R.string.view_detail),
            color = Color.LightGray,
            routineView = { routine ->
                Exercise(
                    exercise = Exercise(
                        "벤치프레스",
                        5,
                        80,
                        4
                    )
                )
            },
            routineList = list,
            onClickAdd = {},
            isAddBtnVisible = false
        )
    }
}

@Composable
fun Exercise(exercise: Exercise) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = exercise.title,
            style = LocalFitNoteTypography.current.titleDefault,
            color = GrayScaleDarkGray2
        )
        Spacer(modifier = Modifier.weight(2f))
        Text(
            text = "${exercise.setCount}세트 ${exercise.weight}kg ${exercise.count}회",
            style = LocalFitNoteTypography.current.textDefault,
            color = GrayScaleMidGray3
        )
    }
}

private val mockUiState = LoadLessonState()

@FigmaPreview
@Composable
internal fun PreviewLoadLesson() {
    FitNoteTheme {
        LoadLesson(
            lessonState = mockUiState,
            popBackStack = {},
            onLoadButtonClick = {},
        )
    }
}
