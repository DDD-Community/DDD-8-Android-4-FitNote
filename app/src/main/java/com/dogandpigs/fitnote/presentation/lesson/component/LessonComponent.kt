package com.dogandpigs.fitnote.presentation.lesson.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.ExerciseSetItemText
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.util.format

@Composable
internal fun ExerciseUnfold(
    text: String,
    onClickFold: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickFold()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            tint = GrayScaleMidGray2,
            contentDescription = null,
        )
    }
}

@Composable
internal fun ExerciseFold(
    onClickFold: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickFold()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.string_fold),
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            tint = GrayScaleMidGray2,
            contentDescription = null,
        )
    }
}

@Composable
internal fun ExerciseColumn(
    exercise: Exercise,
    Title: @Composable () -> Unit,
    foldText: String,
    ItemButton: @Composable (Int, Exercise.ExerciseSet) -> Unit,
    BottomButton: @Composable (() -> Unit)? = null,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    onChangeWeight: (String, Int) -> Unit,
    onChangeCount: (String, Int) -> Unit,
) {
    var fold by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(exercise.isFold) {
        fold = exercise.isFold
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(paddingValues)
            .defaultBorder()
            .padding(LocalFitNoteSpacing.current.spacing4),
    ) {
        Title()

        Divider(color = GrayScaleLightGray1)

        if (fold) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
            ExerciseUnfold(
                text = foldText,
                onClickFold = {
                    fold = !fold
                },
            )
        } else {
            exercise.sets.forEachIndexed { index, exerciseSet ->
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                ExerciseSetItem(
                    exerciseSet = exerciseSet,
                    Button = {
                        ItemButton(index, exerciseSet)
                    },
                    onChangeWeight = { value ->
                        onChangeWeight(value, index)
                    },
                    onChangeCount = { value ->
                        onChangeCount(value, index)
                    },
                )
            }

            BottomButton?.also { BottomButton ->
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
                BottomButton()
            }

            HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            ExerciseFold(
                onClickFold = {
                    fold = !fold
                },
            )
        }
    }
}

@Composable
private fun ExerciseSetItem(
    exerciseSet: Exercise.ExerciseSet,
    Button: @Composable () -> Unit,
    onChangeWeight: (String) -> Unit,
    onChangeCount: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExerciseSetItemText(
            text = stringResource(id = R.string.some_set, exerciseSet.setIndex)
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonWeightTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = exerciseSet.weight.format(),
            onValueChange = onChangeWeight,
            enabled = true,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonCountTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = exerciseSet.count.format(),
            onValueChange = onChangeCount,
            enabled = true,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        Button()
    }
}
