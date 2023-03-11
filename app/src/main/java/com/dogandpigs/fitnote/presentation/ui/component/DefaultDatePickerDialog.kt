package com.dogandpigs.fitnote.presentation.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalView
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

    val width = (LocalView.current.width.dp / 4) - 12.dp

    if (visible) {
        // TODO 시간 보정
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = dateMilliSeconds,
        )
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                DefaultPositiveButton(
                    modifier = Modifier
                        .width(width)
                        .fillMaxHeight(),
                    positiveText = "확인",
                    onClickPositive = {
                        onClickConfirmButton(datePickerState.selectedDateMillis)
                        onDismissRequest()
                    },
                )
            },
            dismissButton = {
                DefaultNegativeButton(
                    modifier = Modifier
                        .width(width)
                        .fillMaxHeight(),
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
