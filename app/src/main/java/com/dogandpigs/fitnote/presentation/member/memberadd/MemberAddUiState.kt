package com.dogandpigs.fitnote.presentation.member.memberadd

internal data class MemberAddUiState(
    val name: String = "",
    val profileImgUrl: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val gender: Gender = Gender.MALE,
    val isAddSuccess: Boolean = false
) {
    enum class Gender(
        val value: Int,
        val text: String,
    ) {
        MALE(1, "남성"),
        FEMALE(2, "여성"),
    }
}
