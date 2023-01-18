package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.*

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
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    var stroke by remember { mutableStateOf(1) } // Stroke State
    
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expand = !expand
                        stroke = if (expand) 2 else 1
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center // Control the header Alignment over here.
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown, tint = color, // Icon Color
                    contentDescription = "Drop Down Arrow"
                )
                Text(
                    text = header,
                    color = GrayScaleMidGray2, // Header Color
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    style = LocalFitNoteTypography.current.buttonSmall,
                    
                    )
            }
            HeightSpacer(height = 8.dp)
            if (expand) {
                Column(modifier = Modifier.fillMaxSize()) {
                    for (routine in routineList) {
                        routineView(routine)
                        HeightSpacer(height = 4.dp)
                    }
                    if (isAddBtnVisible) {
                        OutlinedButton(
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
            }
        }
    }
}