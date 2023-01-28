package com.dogandpigs.fitnote.presentation.member.memberlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.DebugMenu
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberListScreen(
    viewModel: MemberListViewModel = hiltViewModel(),
    popBackStack: () -> Unit = {},
    registration: Boolean = false,
    navigateToMemberDetail: () -> Unit = {},
    navigateToMemberAdd: () -> Unit = {},
    navigateToLesson: () -> Unit = {},
    navigateToSetting: () -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var isShowDebugMenu by rememberSaveable { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = "회원목록",
        topBarActions = {
            IconButton(onClick = {
                // TODO 설정으로 이동
//                navigateToSetting
                isShowDebugMenu = !isShowDebugMenu
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_setting),
                    contentDescription = "Setting"
                )
            }
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    MemberListHeader(myName = state.myName, profileImgUrl = state.profileImgUrl)
                    Divider(
                        color = GrayScaleMidGray2, modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                    MemberList(
                        userList = state.userList,
                        popBackStack = popBackStack,
                        onClickMemberDetail = navigateToMemberDetail,
                        onClickMemberAdd = navigateToMemberAdd,
                        onClickLesson = navigateToLesson,
                        onClickSetting = navigateToSetting,
                    )
                }
            }

            DefaultBottomLargePositiveButton(
                positiveText = stringResource(id = R.string.btn_add_member),
                onClickPositive = navigateToMemberAdd,
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            }

            if (isShowDebugMenu) {
                DebugMenu(
                    "회원 상세 정보" to navigateToMemberDetail,
                    "회원 추가" to navigateToMemberAdd,
                    "수업" to navigateToLesson,
                    "설정" to navigateToSetting,
                )
            }
        }
    }
}

@Composable
private fun MemberListHeader(
    myName: String, profileImgUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(vertical = 32.dp, horizontal = 16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = myName)
    }
}

// 회원 추가
// 회원 상세
// 수업 목록
// 설정
@Composable
private fun MemberList(
    userList: List<MemberUiModel>,
    popBackStack: () -> Unit = {},
    onClickMemberDetail: () -> Unit = {},
    onClickMemberAdd: () -> Unit = {},
    onClickLesson: () -> Unit = {},
    onClickSetting: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(vertical = 24.dp, horizontal = 16.dp))
    ) {
        Text(text = "회원 ${userList.size}", color = GrayScaleMidGray3)
        userList.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 24.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item.userName)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GrayScaleLightGray2)
                ) {
                    Text(text = "수업 목록", color = GrayScaleMidGray3)
                }
            }
        }
    }
}

private val previewUiState =
    MemberListUiState(myName = "김코치", profileImgUrl = "", userList = (0..30).map {
        MemberUiModel(id = it.toLong(), userName = "이름 $it")
    })

@FigmaPreview
@Composable
private fun PreviewMemberListScreen() {
    val viewModel = MemberListViewModel().apply {
        setState { previewUiState }
    }
    FitNoteTheme {
        MemberListScreen(viewModel = viewModel)
    }
}

@ComponentPreview
@Composable
private fun PreviewMemberListHeader() {
    FitNoteTheme {
        MemberListHeader(
            myName = previewUiState.myName, profileImgUrl = previewUiState.profileImgUrl
        )
    }
}

@ComponentPreview
@Composable
private fun PreviewMemberList() {
    FitNoteTheme {
        MemberList(userList = previewUiState.userList)
    }
}
