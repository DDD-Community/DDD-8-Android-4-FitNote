package com.dogandpigs.fitnote.presentation.member.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextFieldLabel
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextFieldPlaceholder
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextFieldSuffix
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary
import com.dogandpigs.fitnote.presentation.util.format
import java.util.Date


@Composable
internal fun MemberInfoList(
    uiState: MemberUiState,
    onChangeName: (String) -> Unit,
    onChangeGender: (MemberUiState.Gender) -> Unit,
    onChangeHeight: (String) -> Unit,
    onChangeWeight: (String) -> Unit,
    onClickDate: () -> Unit,
) {
    Column {
        DefaultTextField(
            value = uiState.name,
            onValueChange = onChangeName,
            label = {
                DefaultTextFieldLabel(text = stringResource(id = R.string.name))
            },
            placeholder = {
                DefaultTextFieldPlaceholder(text = stringResource(id = R.string.default_name))
            },
        )
        MemberInfoItem(
            text = stringResource(id = R.string.registration_date),
        ) {
            DateComponent(
                dateMilliSeconds = uiState.dateMillis,
                onShowDatePickerDialog = onClickDate,
            )
        }

        MemberInfoItem(
            text = stringResource(id = R.string.gender),
        ) {
            GenderComponent(
                selectedGender = uiState.gender,
                onClick = onChangeGender,
            )
        }

        DefaultTextField(
            value = uiState.height,
            onValueChange = onChangeHeight,
            label = {
                DefaultTextFieldLabel(text = stringResource(id = R.string.height))
            },
            placeholder = {
                DefaultTextFieldPlaceholder(text = stringResource(id = R.string.default_height))
            },
            suffix = {
                DefaultTextFieldSuffix(text = stringResource(id = R.string.height_unit))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        DefaultTextField(
            value = uiState.weight,
            onValueChange = onChangeWeight,
            label = {
                DefaultTextFieldLabel(text = stringResource(id = R.string.weight))
            },
            placeholder = {
                DefaultTextFieldPlaceholder(text = stringResource(id = R.string.default_weight))
            },
            suffix = {
                DefaultTextFieldSuffix(text = stringResource(id = R.string.weight_unit))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
private fun MemberInfoItem(
    text: String,
    content: @Composable () -> Unit,
) {
    HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
    Column(
        modifier = Modifier.padding(
            horizontal = LocalFitNoteSpacing.current.spacing5,
        )
    ) {
        DefaultText(
            text = text,
            color = GrayScaleMidGray2,
            style = LocalFitNoteTypography.current.textSmall,
        )
        HeightSpacer(height = 10.dp)

        content()
    }
    HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
}

@Composable
private fun DateComponent(
    dateMilliSeconds: Long,
    onShowDatePickerDialog: () -> Unit,
) {
    ClickableText(
        text = AnnotatedString(Date(dateMilliSeconds).format()),
        style = LocalFitNoteTypography.current.textDefault,
        onClick = {
            onShowDatePickerDialog()
        }
    )
}

@Composable
private fun GenderComponent(
    selectedGender: MemberUiState.Gender,
    onClick: (MemberUiState.Gender) -> Unit,
) {
    Row {
        GenderOutlinedButton(
            gender = MemberUiState.Gender.MALE,
            isSelected = (selectedGender == MemberUiState.Gender.MALE),
            onClick = onClick,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)
        GenderOutlinedButton(
            gender = MemberUiState.Gender.FEMALE,
            isSelected = (selectedGender == MemberUiState.Gender.FEMALE),
            onClick = onClick,
        )
    }
}

@Composable
private fun GenderOutlinedButton(
    gender: MemberUiState.Gender,
    isSelected: Boolean,
    onClick: (MemberUiState.Gender) -> Unit
) {
    OutlinedButton(
        onClick = { onClick(gender) },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                SubPrimary
            } else {
                GrayScaleLightGray1
            },
        ),
        border = BorderStroke(
            1.dp, if (isSelected) {
                BrandPrimary
            } else {
                GrayScaleLightGray2
            }
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        DefaultText(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp,
            ),
            text = gender.text,
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall,
        )
    }
}
