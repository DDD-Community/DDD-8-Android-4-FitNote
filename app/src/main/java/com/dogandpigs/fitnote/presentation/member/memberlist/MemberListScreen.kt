package com.dogandpigs.fitnote.presentation.member.memberlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.dogandpigs.fitnote.BuildConfig
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DebugMenu
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberListScreen(
    viewModel: MemberListViewModel = hiltViewModel(),
    registration: Boolean = false,
    navigateToMemberDetail: () -> Unit = {},
    navigateToMemberAdd: () -> Unit = {},
    navigateToMemberLessonList: () -> Unit = {},
    navigateToSetting: () -> Unit = {}
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    var isShowDebugMenu by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

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
                    .verticalScroll(rememberScrollState())
            ) {
                MemberListHeader(
                    myName = uiState.myName,
                    profileImgUrl = uiState.profileImgUrl,
                )

                Divider(
                    color = GrayScaleLightGray2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp),
                )

                MemberList(
                    userList = uiState.userList,
                    onClickMemberDetail = navigateToMemberDetail,
                    onClickMemberLessonList = navigateToMemberLessonList,
                )
            }

            DefaultBottomLargePositiveButton(
                positiveText = stringResource(id = R.string.btn_add_member),
                onClickPositive = navigateToMemberAdd,
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            }

            if (isShowDebugMenu && BuildConfig.DEBUG) {
                DebugMenu(
                    "회원 상세 정보" to navigateToMemberDetail,
                    "회원 추가" to navigateToMemberAdd,
                    "수업" to navigateToMemberLessonList,
                    "설정" to navigateToSetting,
                )
            }
        }
    }
}

@Composable
private fun MemberListHeader(
    myName: String,
    profileImgUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        HeightSpacer(height = 23.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            WidthSpacer(width = 20.dp)

            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(20.dp))

            DefaultText(
                text = myName,
                color = GrayScaleDarkGray2,
                style = LocalFitNoteTypography.current.titleLarge,
            )
        }
        HeightSpacer(height = 31.dp)
    }
}

// 회원 추가
// 회원 상세
// 수업 목록
// 설정
@Composable
private fun MemberList(
    userList: List<MemberUiModel>,
    onClickMemberDetail: () -> Unit = {},
    onClickMemberLessonList: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(vertical = 24.dp, horizontal = 16.dp))
    ) {
        DefaultText(
            text = "회원 ${userList.size}",
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.textSmall,
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

        userList.forEach { item ->
            MemberListItem(
                item = item,
                onClickText = onClickMemberDetail,
                onClickButton = onClickMemberLessonList,
            )
        }
        HeightSpacer(height = 75.dp)
    }
}

@Composable
private fun MemberListItem(
    item: MemberUiModel,
    onClickText: () -> Unit,
    onClickButton: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultText(
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Max)
                .clickable {
                    onClickText()
                },
            text = item.userName,
            color = GrayScaleDarkGray2,
            style = LocalFitNoteTypography.current.textDefault,
        )
        Button(
            onClick = onClickButton,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GrayScaleLightGray2,
            )
        ) {
            DefaultText(
                text = "수업 목록",
                color = GrayScaleMidGray3,
                style = LocalFitNoteTypography.current.buttonSmall,
            )
        }
    }
    HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
}

private val previewUiState =
    MemberListUiState(
        myName = "김코치",
        profileImgUrl = "",
        userList = (0..30).map {
            MemberUiModel(id = it.toLong(), userName = "이름 $it")
        }
    )

@ComponentPreview
@Composable
private fun PreviewMemberListHeader() {
    FitNoteTheme {
        MemberListHeader(
            myName = previewUiState.myName,
            profileImgUrl = previewUiState.profileImgUrl,
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