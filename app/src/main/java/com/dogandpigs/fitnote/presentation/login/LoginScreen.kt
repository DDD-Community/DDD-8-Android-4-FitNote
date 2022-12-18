package com.dogandpigs.fitnote.presentation.login

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes
import com.dogandpigs.fitnote.presentation.navigation.navigateToScreen
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    Login(
        viewModel = viewModel,
        uiState = viewModel.uiState,
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Login(
    viewModel: LoginViewModel,
    uiState: LoginUiState,
    navigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "로그인",
                    fontWeight = FontWeight.Bold
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
        },
        content = {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .background(color = Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                        }
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
                            .padding(20.dp, 0.dp)
                            .background(Color.Transparent),
                        value = pwd,
                        onValueChange = { textValue ->
                            pwd = textValue
                        },
                        placeholder = {
                            Text("pwd")
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(20.dp, 0.dp)
                            .background(color = Color.White),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "비밀번호를 잊어버리셨나요? ")
                        TextButton(onClick = {}) {
                            Text(
                                text = "비밀번호 찾기",
                                color = Color.Blue
                            )
                        }
                    }
                }

                OutlinedButton(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(0.dp, 10.dp)
                        .background(Color.Transparent)
                        .align(Alignment.BottomCenter),
                    onClick = {
                        viewModel.login(email.text, pwd.text)
                        navigateToHome()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, contentColor = Color.Black
                    )
                ) {
                    Text(text = "다음")
                }
            }
        }
    )
}

private val mockUiState = LoginUiState(
    title = "mock LoginUiState title"
)

@FigmaPreview
@Composable
private fun PreviewLogin() {
    FitNoteTheme {
        Login(
            viewModel = hiltViewModel(),
            uiState = mockUiState
        ) {}
    }
}