package com.dogandpigs.fitnote.presentation.lesson.addlesson

import android.app.DatePickerDialog
import android.app.FragmentManager
import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.MainActivity
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

@Composable
internal fun AddLessonScreen(
    viewModel: AddLessonViewModel, navigateToHome: () -> Unit, navigateToLoad: () -> Unit
) {
    AddLesson(
        uiState = viewModel.uiState,
        navigateToHome = navigateToHome,
        navigateToLoad = navigateToLoad
    )
}

@Composable
private fun AddLesson(
    uiState: AddLessonUiState, navigateToHome: () -> Unit,
    navigateToLoad: () -> Unit
) {
    val navController = rememberNavController()
    
    FitNoteScaffold(
        topBarTitle = "수업 추가",
        topBarTitleFontSize = 16.sp,
        onClickTopBarNavigationIcon = { navController.navigateUp() },
        topBarNavigationIconImageVector = Icons.Filled.Close,
        topBarActions = {
            TextButton(onClick = {}) {
                Text(
                    text = "불러오기",
                    color = colorResource(id = R.color.brand_primary)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddLessonCard()
            }
            AddButton()
        }
    }
}

@Composable
private fun AddButton() {
    val buttonHeight = 52.dp
    val paddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 24.dp,
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        // TODO: 버튼 공통화
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(buttonHeight)
//                    .padding(paddingValues)
                .background(Color.Transparent),
            onClick = {},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary
            )
        ) {
            Text(
                text = "다음",
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White
            )
        }
        
    }
}

@Composable
private fun AddLessonCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 15.dp)
        ) {
            DateLabel()
            Spacer(modifier = Modifier.height(20.dp))
            InputLesson()
            Spacer(modifier = Modifier.height(20.dp))
            AddExercise()
        }
    }
}

@Composable
private fun DateLabel() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.border_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp, 16.dp),
    ) {
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 24.dp),
            text = "날짜",
            color = Color.Black,
            fontSize = 12.sp
        )
        ClickableText(
            text = AnnotatedString("2022년 12월 25일"),
            style = TextStyle.Default,
            onClick = {
                showDatePicker(context as AppCompatActivity)
            }
        )
    }
}

private fun showDatePicker(activity: AppCompatActivity) {
    Log.d("testTAG", "showDatePicker: ")
    val fm = activity.supportFragmentManager
    val picker = MaterialDatePicker.Builder.datePicker().build()
    fm.let {
        
        picker.show(fm, picker.toString())
        picker.addOnPositiveButtonClickListener {
        
        }
    }
}

fun Context.getActivity(): AppCompatActivity? {
    var currentContext = this
    while (currentContext is ContextWrapper) {
        if (currentContext is AppCompatActivity) {
            return currentContext
        }
        currentContext = currentContext.baseContext
    }
    return null
}

@Composable
private fun InputLesson() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.border_gray),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp, 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1F)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.border_gray),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(15.dp, 15.dp),
                text = "운동명",
                color = Color.Black,
                fontSize = 12.sp
            )
            IconButton(
                modifier = Modifier.wrapContentWidth(),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "Back"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Routine()
        Spacer(modifier = Modifier.height(20.dp))
        ExpandableCard(header = "세트별 편집", description = "ㅎㅁㄴㅇㅎㅁㄴㅇㅎ", color = Color.LightGray)
    }
}

@Composable
private fun Routine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.border_gray),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp, 15.dp),
            text = "0세트",
            color = Color.Black,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .weight(1F)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.border_gray),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp, 15.dp),
            text = "0kg",
            color = Color.Black,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .weight(1F)
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.border_gray),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(15.dp, 15.dp),
            text = "0회",
            color = Color.Black,
            fontSize = 12.sp
        )
    }
}

@Composable
private fun AddExercise() {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Box(
        Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            drawRoundRect(
                color = Color.LightGray,
                style = stroke,
                cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.Black, // Icon Color
                contentDescription = "Add Exercise"
            )
            Text(
                text = "운동 추가",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}
@Composable
fun Calender() {
    
    var datePicked by remember { mutableStateOf("1") }
    
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    
    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    
    
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            datePicked = "$dayOfMonth/$month/$year"
        }, year, month, day
    )
    
    OutlinedButton(onClick = { datePickerDialog.show() }) {
        Text(text = "Pick Date")
    }
}

private val mockUiState = AddLessonUiState(
    title = "mock AddLesson title"
)

@FigmaPreview
@Composable
private fun PreviewAddLesson() {
    FitNoteTheme() {
        AddLesson(uiState = mockUiState, navigateToHome = {}, navigateToLoad = {})
    }
}
