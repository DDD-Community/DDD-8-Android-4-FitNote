package com.dogandpigs.fitnote.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel,
    navigateToHome: () -> Unit
) {
    Login(
//        viewModel = viewModel,
        uiState = viewModel.uiState,
        navigateToHome = navigateToHome
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Login(
//    viewModel: LoginViewModel,
    uiState: LoginUiState,
    navigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }
    val navController = rememberNavController()

    FitNoteScaffold(
        topBarTitle = "로그인",
        onClickTopBarNavigationIcon = { navController.navigateUp() },
    ) {
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
                        Text(text = stringResource(id = R.string.email))
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
                        Text(stringResource(id = R.string.placeholder_email))
                    }
                )
                TextField(
                    label = {
                        Text(text = stringResource(id = R.string.password))
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
                    Text(text = stringResource(id = R.string.text_forget_pwd))
                            
                    TextButton(onClick = {}) {
                        Text(
                            text = stringResource(id = R.string.btn_forget_pwd),
                            color = Color.Blue
                        )
                    }
                }
            }

            CompleteButton(stringResource(id = R.string.btn_login), onClick = {})
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
        Login(
//            viewModel = hiltViewModel(),
            uiState = mockUiState
        ) {}
    }
}
