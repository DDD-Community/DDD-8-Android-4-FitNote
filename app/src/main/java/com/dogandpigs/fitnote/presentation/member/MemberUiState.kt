package com.dogandpigs.fitnote.presentation.member

internal data class MemberUiState(
    val name: String = "",
    val profileImgUrl: String = "",
    val createDate: String = "",
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
        NONE(0, "없음"),
        MALE(1, "남성"),
        FEMALE(2, "여성"),
    }
}
