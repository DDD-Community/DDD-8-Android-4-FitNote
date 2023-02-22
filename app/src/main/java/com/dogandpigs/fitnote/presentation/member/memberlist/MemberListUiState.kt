package com.dogandpigs.fitnote.presentation.member.memberlist

import com.dogandpigs.fitnote.data.source.remote.response.ListResponse

internal data class MemberListUiState(
    val myName: String = "",
    val profileImgUrl: String = "",
    val userList: List<MemberUiModel> = listOf(),
    // TODO 수정
    val trainerIfo: ListResponse? = null,
) {
    val userListTitle: String
        get() = "회원 ${userList.size}"
}
