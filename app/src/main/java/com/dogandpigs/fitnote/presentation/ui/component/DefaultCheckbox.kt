package com.dogandpigs.fitnote.presentation.ui.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary

@Composable
fun DefaultCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = CheckboxDefaults.colors(
            checkedColor = BrandPrimary,
        )
    )
}
