package com.dogandpigs.fitnote.presentation.memberlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun MemberListScreen(
    viewModel: MemberListViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToMemberDetail: () -> Unit,
    navigateToAddMember: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    Box {
        MemberList(
            uiState = viewModel.uiState,
            popBackStack = popBackStack,
            onClickMemberDetail = navigateToMemberDetail,
            onClickAddMember = navigateToAddMember,
            onClickLesson = navigateToLesson,
            onClickSetting = navigateToSetting,
        )
    }
}

// 회원 추가
// 회원 상세
// 수업 목록
// 설정
@Composable
private fun MemberList(
    uiState: MemberListUiState,
    popBackStack: () -> Unit,
    onClickMemberDetail: () -> Unit,
    onClickAddMember: () -> Unit,
    onClickLesson: () -> Unit,
    onClickSetting: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = "회원목록",
        topBarTitleFontSize = 20.sp,
        onClickTopBarNavigationIcon = popBackStack
    ) {
        Box(modifier = Modifier.padding(it)) {
        }
    }
}

private val previewUiState = MemberListUiState(
    title = "수업 목록",
    userName = "나초보 회원님",
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        MemberList(
            uiState = previewUiState,
            popBackStack = {},
            onClickMemberDetail = {},
            onClickAddMember = {},
            onClickLesson = {},
            onClickSetting = {},
        )
    }
}
