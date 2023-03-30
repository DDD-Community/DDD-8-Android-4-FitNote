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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.BuildConfig
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DebugMenu
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

@Composable
internal fun MemberListScreen(
    viewModel: MemberListViewModel = hiltViewModel(),
    navigateToMemberDetail: (Int) -> Unit,
    navigateToMemberAdd: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
    navigateToSetting: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    // FIXME registration true일 경우 MemberListScreen -> 다른 화면 -> Back 시 토스트가 계속 보여지는 이슈
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    FitNoteScaffold(
        topBarTitle = "회원목록",
        topBarActions = {
            IconButton(onClick = {
                navigateToSetting()
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

            BottomPositiveButton(
                text = stringResource(id = R.string.btn_add_member),
                onClick = navigateToMemberAdd,
            )
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
    onClickMemberDetail: (Int) -> Unit,
    onClickMemberLessonList: (Int) -> Unit,
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
    onClickText: (Int) -> Unit,
    onClickButton: (Int) -> Unit,
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
                    onClickText(item.id)
                },
            text = item.userName,
            color = GrayScaleDarkGray2,
            style = LocalFitNoteTypography.current.textDefault,
        )
        Button(
            onClick = {
                onClickButton(item.id)
            },
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
            MemberUiModel(id = it, userName = "이름 $it")
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
        MemberList(
            userList = previewUiState.userList,
            onClickMemberDetail = {},
            onClickMemberLessonList = {},
        )
    }
}
