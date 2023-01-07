package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

internal data class MemberLessonListUiState(
    val userName: String = "",
    val tabs: List<Tab> = previewTabs,
) {
    data class Tab(
        val isSelected: Boolean,
        val name: String,
        val emptySubText: String = "",
        val lessons: List<Lesson>
    ) {
        data class Lesson(
            val dateString: String = "",
            val exercises: List<String> = emptyList(),
        )
    }
}
