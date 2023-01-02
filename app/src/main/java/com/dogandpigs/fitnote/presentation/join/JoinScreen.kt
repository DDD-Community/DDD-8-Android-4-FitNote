package com.dogandpigs.fitnote.presentation.join

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel,
    popBackStack: () -> Unit
) {
    Join(
        viewModel = viewModel,
        uiState = viewModel.uiState,
        popBackStack = popBackStack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Join(
    viewModel: JoinViewModel,
    uiState: JoinUiState,
    popBackStack: () -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }
    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPwdError by remember { mutableStateOf(false) }
    FitNoteScaffold(
        topBarTitle = "가입하기",
        onClickTopBarNavigationIcon = popBackStack,
    ) { paddingValue ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    isError = isNameError,
                    label = {
                        Text(text = "name")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        errorIndicatorColor = Color.Red
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .background(Color.Transparent),
                    value = name,
                    onValueChange = { newText ->
                        isNameError = true
                        name = newText
                    },
                    placeholder = {
                        Text("name")
                    },
                )
                TextField(
                    isError = isEmailError,
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
                    isError = isPwdError,
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
    
            CompleteButton("다음", onClick = {})
        }
    }
}

private val mockUiState = JoinUiState(
    title = "mock JoinUiState title"
)

@FigmaPreview
@Composable
private fun PreviewJoin() {
    FitNoteTheme {
        Join(
            viewModel = hiltViewModel(),
            uiState = mockUiState
        ) {}
    }
}
