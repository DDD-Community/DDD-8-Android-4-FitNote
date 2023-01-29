package com.dogandpigs.fitnote.presentation.member.memberlist

import com.dogandpigs.fitnote.data.source.remote.model.TrainerInfo

internal data class MemberListUiState(
    val myName: String = "김코치",
    val profileImgUrl: String = "",
    val userList: List<MemberUiModel> = listOf(),
    val trainerIfo: TrainerInfo = TrainerInfo()
) {
    val userListTitle: String
        get() = "회원 ${userList.size}"
}
