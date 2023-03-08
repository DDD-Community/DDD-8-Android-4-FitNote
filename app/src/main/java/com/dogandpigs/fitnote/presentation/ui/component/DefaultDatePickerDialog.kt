package com.dogandpigs.fitnote.presentation.ui.component

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

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

    if (visible) {
        // TODO 시간 보정
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = dateMilliSeconds,
        )
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickConfirmButton(datePickerState.selectedDateMillis)
                        onDismissRequest()
                    },
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
