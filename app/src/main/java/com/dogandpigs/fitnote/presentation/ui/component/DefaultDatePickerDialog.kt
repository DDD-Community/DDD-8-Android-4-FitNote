package com.dogandpigs.fitnote.presentation.ui.component

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleWhite

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DefaultDatePickerDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    onClickConfirmButton: (Long?) -> Unit,
    dateMilliSeconds: Long,
) {
    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier)

    val width = 120.dp // TODO 추후 버튼 크기가 자동으로 계산되게 수정

    if (visible) {
        // TODO 시간 보정
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = dateMilliSeconds,
        )
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                DefaultPositiveButton(
                    width = width,
                    positiveText = "확인",
                    onClickPositive = {
                        onClickConfirmButton(datePickerState.selectedDateMillis)
                        onDismissRequest()
                    },
                )
            },
            dismissButton = {
                DefaultNegativeButton(
                    width = width,
                    negativeText = "취소",
                    onClickNegative = onDismissRequest,
                )
            },
            colors = DatePickerDefaults.colors(
                containerColor = GrayScaleWhite,
            )
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    containerColor = GrayScaleWhite,
                    currentYearContentColor = BrandPrimary,
                    selectedYearContentColor = GrayScaleWhite,
                    selectedYearContainerColor = BrandPrimary,
                    selectedDayContentColor = GrayScaleWhite,
                    selectedDayContainerColor = BrandPrimary,
                    todayContentColor = BrandPrimary,
                    todayDateBorderColor = BrandPrimary,
                ),
            )
        }
    }
}
