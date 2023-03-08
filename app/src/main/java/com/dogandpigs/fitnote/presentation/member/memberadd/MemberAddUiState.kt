package com.dogandpigs.fitnote.presentation.member.memberadd

internal data class MemberAddUiState(
    val name: String = "",
    val profileImgUrl: String = "",
    val height: String = "160",
    val weight: String = "50",
    val gender: Gender = Gender.MALE,
    val isAddSuccess: Boolean = false,
    val toast: String = "",
) {
    enum class Gender(
        val value: Int,
        val text: String,
    ) {
        MALE(1, "남성"),
        FEMALE(2, "여성"),
    }
}
