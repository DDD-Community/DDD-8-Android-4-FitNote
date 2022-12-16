package com.dogandpigs.fitnote.presentation.lesson

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun LessonScreen(
    viewModel: LessonViewModel,
    navigateToHome: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    Box {
        Lesson(
            uiState = viewModel.uiState,
            navigateToHome = navigateToHome,
            navigateToSetting = navigateToSetting,
        )
    }
}

@Composable
private fun Lesson(
    uiState: LessonUiState,
    navigateToHome: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    Box {
        Column {
            DefaultAppBar(
                navigateToHome = navigateToHome,
                navigateToSetting = navigateToSetting,
            )
            LessonTabList()
        }
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(
                    bottom = 36.dp,
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            AddLessonButton()
        }
    }
}

// TODO move To Default component
@Composable
private fun DefaultAppBar(
    title: String = "수업 목록",
    userName: String = "나초보 회원님",
    navigateToHome: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    val height = 140.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(
                start = 34.dp,
            )
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .padding(
                    end = 19.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = title,
                fontSize = 24.sp,
            )
            IconButton(
                modifier = Modifier.size(28.dp),
                onClick = navigateToHome
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_house),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                modifier = Modifier.size(28.dp),
                onClick = navigateToSetting
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }
        Text(
            text = userName,
            fontSize = 20.sp,
        )
    }
}

data class Tab(
    val isSelected: Boolean,
    val name: String,
    val list: List<Lesson>
) {
    data class Lesson(
        val name: String
    )
}

private val mockTabs = listOf(
    Tab(
        isSelected = true, name = "예정된 수업", list = listOf()
    ),
    Tab(
        isSelected = false, name = "완료한 수업", list = listOf()
    )
)

@Composable
private fun LessonTabList(
    tabs: List<Tab> = mockTabs,
) {
    Row {
        for (tab in tabs) {
            LessonTab(
                modifier = Modifier
                    .weight(1F)
                    .height(40.dp),
                title = tab.name,
                isSelected = tab.isSelected,
            )
            if (tab.isSelected) {
//                LessonList(tab.list)
            }
        }
    }
}

@Composable
private fun LessonTab(
    modifier: Modifier,
    title: String,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center,
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    if (isSelected) {
                        3.dp
                    } else {
                        1.dp
                    }
                )
                .background(
                    Color.Black
                )
        )
    }
}

@Composable
private fun AddLessonButton() {
    OutlinedButton(
        onClick = { },
        modifier = Modifier.size(
            width = 153.dp,
            height = 69.dp,
        ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Gray
        )
    ) {
        Text(
            text = "수업 추가",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.Black,
        )
    }
}

private val mockUiState = LessonUiState(
    title = "mock SplashUiState title"
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        Lesson(
            uiState = mockUiState,
            navigateToHome = {},
            navigateToSetting = {},
        )
    }
}
