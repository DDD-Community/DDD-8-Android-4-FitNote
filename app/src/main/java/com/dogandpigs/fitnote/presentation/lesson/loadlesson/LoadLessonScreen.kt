package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.DefaultValue
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultSpacer
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary

@Composable
internal fun LoadLessonScreen(
    viewModel: LoadLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToAddLessonWithLoad: (Int, Int) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    LaunchedEffect(uiState.selectedMemberId) {
        viewModel.getLessonList()
    }

    LaunchedEffect(uiState.isLoad) {
        if (uiState.isLoad) {
            navigateToAddLessonWithLoad(uiState.selectedMemberId, uiState.selectedRoutineId)
            viewModel.setIsLoad(false)
        }
    }

    LoadLesson(
        uiState = uiState,
        popBackStack = popBackStack,
        onLoadButtonClick = viewModel::load,
        onMemberNameClick = viewModel::setSelectedMemberId,
        onRoutineClick = viewModel::setSelectedRoutineId,
        onRoutineFold = viewModel::toggleFold,
    )
}

@Composable
private fun LoadLesson(
    uiState: LoadLessonUiState,
    popBackStack: () -> Unit,
    onLoadButtonClick: () -> Unit,
    onMemberNameClick: (Int) -> Unit,
    onRoutineClick: (Int) -> Unit,
    onRoutineFold: (Int) -> Unit,
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
                uiState = uiState,
                onMemberNameClick = onMemberNameClick,
                onRoutineClick = onRoutineClick,
                onRoutineFold = onRoutineFold,
            )

            BottomPositiveButton(
                text = stringResource(id = R.string.load),
                enabled = uiState.selectedRoutineId != DefaultValue.ITEM_INDEX_NOT_SELECTED,
                onClick = onLoadButtonClick,
            )
        }
    }
}

@Composable
private fun LoadLessonContent(
    uiState: LoadLessonUiState,
    onMemberNameClick: (Int) -> Unit,
    onRoutineClick: (Int) -> Unit,
    onRoutineFold: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing5)
        RowMemberNameList(
            memberList = uiState.memberList,
            selectedMemberId = uiState.selectedMemberId,
            onMemberNameClick = onMemberNameClick,
        )
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing5)
        ColumnRoutineList(
            routineList = uiState.routineList,
            selectedRoutineId = uiState.selectedRoutineId,
            onRoutineClick = onRoutineClick,
            onRoutineFold = onRoutineFold,
        )
    }
}

@Composable
private fun RowMemberNameList(
    memberList: List<LoadLessonUiState.Member>,
    selectedMemberId: Int,
    onMemberNameClick: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(scrollState)
    ) {
        DefaultSpacer(width = LocalFitNoteSpacing.current.spacing3)
        memberList.forEach { member ->
            MemberNameTag(
                name = member.name,
                isSelected = member.id == selectedMemberId,
                onMemberNameClick = {
                    onMemberNameClick(member.id)
                }
            )
            DefaultSpacer(width = LocalFitNoteSpacing.current.spacing3)
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
            containerColor = if (isSelected) {
                SubPrimary
            } else {
                GrayScaleLightGray1
            },
        ),
        border = BorderStroke(
            1.dp,
            if (isSelected) {
                BrandPrimary
            } else {
                GrayScaleLightGray2
            },
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
private fun ColumnRoutineList(
    routineList: List<LoadLessonUiState.Routine>,
    selectedRoutineId: Int,
    onRoutineClick: (Int) -> Unit,
    onRoutineFold: (Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        routineList.forEachIndexed { index, routine ->
            Routine(
                routine = routine,
                isSelected = routine.id == selectedRoutineId,
                onRoutineClick = {
                    onRoutineClick(routine.id)
                },
                onRoutineFold = {
                    onRoutineFold(index)
                },
            )
            DefaultSpacer(height = LocalFitNoteSpacing.current.spacing4)
        }
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing10)
    }
}

@Composable
private fun Routine(
    routine: LoadLessonUiState.Routine,
    isSelected: Boolean,
    onRoutineClick: () -> Unit,
    onRoutineFold: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = LocalFitNoteSpacing.current.spacing4,
                )
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) {
                        Color(0xFFDFEFFF)
                    } else {
                        GrayScaleWhite
                    }
                )
                .border(
                    1.dp,
                    if (isSelected) {
                        BrandPrimary
                    } else {
                        GrayScaleLightGray2
                    },
                    RoundedCornerShape(10.dp),
                )
                .padding(
                    horizontal = LocalFitNoteSpacing.current.spacing4,
                )
        ) {
            DefaultSpacer(height = LocalFitNoteSpacing.current.spacing2) // Figma 16 -> 8
            RoutineTitleAndRadioButton(
                routine = routine,
                isSelected = isSelected,
                onRadioButtonClick = onRoutineClick,
            )
            DefaultSpacer(height = 12.dp) // Figma 20 -> 12
            ExerciseNameList(
                exerciseNameList = routine.exerciseNameList,
                isSelected = isSelected,
            )
            DefaultSpacer(height = LocalFitNoteSpacing.current.spacing4)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        if (isSelected) {
                            SubPrimary
                        } else {
                            GrayScaleLightGray1
                        }
                    )
            )

            ExerciseListAndButton(
                exerciseList = routine.exerciseList,
                visible = routine.exerciseListVisible,
                onRoutineToggle = onRoutineFold,
            )
        }
    }
}

@Composable
private fun RoutineTitleAndRadioButton(
    routine: LoadLessonUiState.Routine,
    isSelected: Boolean,
    onRadioButtonClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DefaultText(
            modifier = Modifier.weight(1f),
            text = routine.title,
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonMedium,
        )
        RadioButton(
            selected = isSelected,
            onClick = onRadioButtonClick,
        )
    }
}

@Composable
private fun ExerciseNameList(
    exerciseNameList: List<String>,
    isSelected: Boolean,
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .horizontalScroll(scrollState)
    ) {
        exerciseNameList.forEach {
            ExerciseNameTag(
                name = it,
                isSelected = isSelected,
            )
            DefaultSpacer(width = LocalFitNoteSpacing.current.spacing4)
        }
    }
}

@Composable
private fun ExerciseNameTag(
    name: String,
    isSelected: Boolean,
) {
    Row(
        modifier = Modifier
            .background(
                if (isSelected) {
                    SubPrimary
                } else {
                    GrayScaleLightGray1
                },
                RoundedCornerShape(5.dp),
            )
    ) {
        DefaultText(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            ),
            text = name,
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonMedium,
        )
    }
}

@Composable
private fun ExerciseListAndButton(
    exerciseList: List<LoadLessonUiState.Routine.Exercise>,
    visible: Boolean,
    onRoutineToggle: () -> Unit,
) {
    if (visible) {
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing5)
        ExerciseList(
            exerciseList = exerciseList,
        )
        DefaultSpacer(height = LocalFitNoteSpacing.current.spacing2)
    }
    ExerciseListVisibleButton(
        visible = visible,
        onRoutineToggle = onRoutineToggle,
    )
}

@Composable
private fun ExerciseList(
    exerciseList: List<LoadLessonUiState.Routine.Exercise>
) {
    Column {
        exerciseList.forEachIndexed { index, exercise ->
            Exercise(exercise = exercise)
            if (index != exerciseList.lastIndex) {
                DefaultSpacer(height = LocalFitNoteSpacing.current.spacing4)
            }
        }
    }
}

@Composable
private fun ExerciseListVisibleButton(
    visible: Boolean,
    onRoutineToggle: () -> Unit,
) {
    DefaultSpacer(height = LocalFitNoteSpacing.current.spacing4)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onRoutineToggle()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DefaultText(
            modifier = Modifier.wrapContentSize(),
            text = if (visible) {
                stringResource(id = R.string.string_fold)
            } else {
                stringResource(id = R.string.show_detail)
            },
            color = GrayScaleMidGray2,
            style = LocalFitNoteTypography.current.buttonSmall,
        )
        DefaultSpacer(width = LocalFitNoteSpacing.current.spacing1)
        Icon(
            imageVector = if (visible) {
                Icons.Default.KeyboardArrowUp
            } else {
                Icons.Default.KeyboardArrowDown
            },
            tint = GrayScaleMidGray2,
            contentDescription = null,
        )
    }
    DefaultSpacer(height = LocalFitNoteSpacing.current.spacing4)
}

@Composable
private fun Exercise(
    exercise: LoadLessonUiState.Routine.Exercise,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DefaultText(
            modifier = Modifier.weight(1f),
            text = exercise.name,
            style = LocalFitNoteTypography.current.titleDefault,
            color = GrayScaleDarkGray2,
        )
        DefaultText(
            text = "${exercise.set}세트 ${exercise.weight}kg ${exercise.count}회",
            style = LocalFitNoteTypography.current.textDefault,
            color = GrayScaleMidGray3,
        )
    }
}

private val mockUiState = LoadLessonUiState()

@FigmaPreview
@Composable
internal fun PreviewLoadLesson() {
    FitNoteTheme {
        LoadLesson(
            uiState = mockUiState,
            popBackStack = {},
            onLoadButtonClick = {},
            onMemberNameClick = {},
            onRoutineClick = {},
            onRoutineFold = {},
        )
    }
}
