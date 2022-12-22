package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    header: String, // Header
    description: String, // Description
    color: Color, // Color
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    var stroke by remember { mutableStateOf(1) } // Stroke State
    Card(modifier = Modifier
        .animateContentSize( // Animation
            animationSpec = tween(
                durationMillis = 400, // Animation Speed
                easing = LinearOutSlowInEasing // Animation Type
            )
        )
        .background(color = Color.White)
//        .padding(8.dp),
//        shape = RoundedCornerShape(10.dp), // Shape
//        border = BorderStroke(stroke.dp, color) // Stroke Width and Color
        ,
        onClick = {
            expand = !expand
            stroke = if (expand) 2 else 1
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center // Control the header Alignment over here.
            ) {
                IconButton(modifier = Modifier.rotate(rotationState), onClick = {
                    expand = !expand
                    stroke = if (expand) 2 else 1
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown, tint = color, // Icon Color
                        contentDescription = "Drop Down Arrow"
                    )
                }
                Text(
                    text = header,
                    color = color, // Header Color
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                )
            }
            if (expand) {
                Text(
                    text = description,
                    color = color, // Description Color
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}