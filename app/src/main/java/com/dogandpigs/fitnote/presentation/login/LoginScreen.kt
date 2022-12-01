package com.dogandpigs.fitnote.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
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

@Composable
private fun Login(
    viewModel: LoginViewModel,
    uiState: LoginUiState,
    navigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 10.dp)
                .background(Color.Transparent),
            value = email,
            onValueChange = {
                email = it
            },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                ) {
                    if (email.text.isEmpty()) {
                        Text("email")
                    }
                    innerTextField()
                }
            },
        )
        BasicTextField(
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 10.dp)
                .background(Color.Transparent),
            value = pwd,
            onValueChange = {
                pwd = it
            },
            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .background(Color.LightGray, RoundedCornerShape(percent = 30))
                        .padding(16.dp)
                ) {
                    if (pwd.text.isEmpty()) {
                        Text("pwd")
                    }
                    innerTextField()
                }
            },
        )
        OutlinedButton(
            modifier = Modifier
                .width(100.dp)
                .padding(0.dp, 10.dp)
                .background(Color.Transparent),
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
        
        TextButton(
            onClick = {}
        ) {
            Text(
                text = "비밀번호 찾기",
                color = Color.Black
            )
        }
    }
}

private val mockUiState = LoginUiState(
    title = "mock LoginUiState title"
)

@FigmaPreview
@Composable
private fun PreviewLogin() {
    FitNoteTheme {
        Login(viewModel = hiltViewModel(), uiState = mockUiState) {}
    }
}