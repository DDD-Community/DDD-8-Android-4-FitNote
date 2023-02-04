package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary

@Composable
internal fun ExpandableCard(
    header: String, // Header
    color: Color, // Color
    routineView: @Composable (routine: Routine) -> Unit,
    routineList: MutableList<Routine>,
    onClickAdd: () -> Unit,
    isAddBtnVisible: Boolean = true
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    var stroke by remember { mutableStateOf(1) } // Stroke State
    var headerText by remember { mutableStateOf(header) }
    var iconVector by remember { mutableStateOf(Icons.Default.KeyboardArrowDown) }

    Card(
        modifier = Modifier
            .animateContentSize( // Animation
                animationSpec = tween(
                    durationMillis = 400, // Animation Speed
                    easing = LinearOutSlowInEasing // Animation Type
                )
            )
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            if (expand) {
                headerText = "접기"
                iconVector = Icons.Default.KeyboardArrowUp
                Column(modifier = Modifier.fillMaxSize()) {
                    for (routine in routineList) {
                        routineView(routine)
                        HeightSpacer(height = 4.dp)
                    }
                    if (isAddBtnVisible) {
                        Button(
                            colors = ButtonDefaults.outlinedButtonColors(
                                disabledContentColor = SubPrimary,
                                disabledContainerColor = BrandPrimary,
                                contentColor = GrayScaleMidGray3,
                                containerColor = GrayScaleLightGray1
                            ),
                            contentPadding = PaddingValues(16.dp, 6.dp),
                            onClick = onClickAdd,
                            modifier = Modifier
                                .align(CenterHorizontally)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(5.dp)
                        ) {
                            Text(text = stringResource(id = R.string.btn_add_set))
                        }
                    }
                }
            } else {
                headerText = header
                iconVector = Icons.Default.KeyboardArrowDown
            }
        }
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .clickable {
                    expand = !expand
                    stroke = if (expand) 2 else 1
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Control the header Alignment over here.
        ) {
            Icon(
                imageVector = iconVector, tint = color, // Icon Color
                contentDescription = "Drop Down Arrow"
            )
            DefaultText(
                text = headerText,
                color = GrayScaleMidGray2,
                style = LocalFitNoteTypography.current.buttonSmall,
            )
        }
    }
}
