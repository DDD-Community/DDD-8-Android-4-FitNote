package com.dogandpigs.fitnote.presentation.member.memberlist

internal data class MemberListUiState(
    val myName: String = "",
    val profileImgUrl: String = "",
    val userList: List<MemberUiModel> = listOf()
) {
    val userListTitle: String
        get() = "회원 ${userList.size}"
}
