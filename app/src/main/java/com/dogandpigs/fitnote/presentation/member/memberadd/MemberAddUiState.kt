package com.dogandpigs.fitnote.presentation.member.memberadd

internal data class MemberAddUiState(
    val name: String = "",
    val profileImgUrl: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val gender: Int = 0,
    val isAddSuccess: Boolean = false
) {
}
