package com.dogandpigs.fitnote.presentation.join

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes
import com.dogandpigs.fitnote.presentation.navigation.navigateToScreen
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel,
    navigateToHome: () -> Unit
) {
    Join(
//        viewModel = viewModel,
        uiState = viewModel.uiState,
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Join(
//    viewModel: JoinViewModel,
    uiState: JoinUiState,
    navigateToHome: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }

    val navController = rememberNavController()

    Log.d("TestTAG", "Join: $navController")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                title = {
                Text(
                    text = "가입하기",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
            )
        }, content = { paddingValue ->
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        label = {
                            Text(text = "name")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp)
                            .background(Color.Transparent),
                        value = name,
                        onValueChange = { newText ->
                            name = newText
                        },
                        placeholder = {
                            Text("name")
                        },
                    )
                    TextField(
                        label = {
                            Text(text = "email")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp)
                            .background(Color.Transparent),
                        value = email,
                        onValueChange = { textValue ->
                            email = textValue
                        },
                        placeholder = {
                            Text("email")
                        },
                    )
                    TextField(
                        label = {
                            Text(text = "password")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp)
                            .background(Color.Transparent),
                        value = pwd,
                        onValueChange = { textValue ->
                            pwd = textValue
                        },
                        placeholder = {
                            Text("password")
                        }
                    )
                    TextField(
                        label = {
                            Text(text = "check password")
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 10.dp)
                            .background(Color.Transparent),
                        value = pwd,
                        onValueChange = { textValue ->
                            pwd = textValue
                        },
                        placeholder = {
                            Text("check password")
                        },
                    )
                }

                OutlinedButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .width(100.dp)
                        .padding(0.dp, 10.dp)
                        .background(Color.Transparent)
                        .align(Alignment.BottomCenter),
                    onClick = {
//                viewModel.join(
//                    name.text,
//                    email.text,
//                    pwd.text
//                )
                    }
                ) {
                    Text(text = "다음")
                }
            }
        }
    )

}

private val mockUiState = JoinUiState(
    title = "mock JoinUiState title"
)

@FigmaPreview
@Composable
private fun PreviewJoin() {
    FitNoteTheme {
        Join(
//            viewModel = hiltViewModel(),
            uiState = mockUiState
        ) {}
    }
}
