package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

internal data class MemberLessonListUiState(
    val userName: String = "",
    val scheduledLessonTab: Tab = previewScheduledLessonTab,
    val completedLessonTab: Tab = previewCompletedLessonTab,
) {
    data class Tab(
        val tabType: TabType,
        val name: String,
        val emptySubText: String = "",
        val lessons: List<Lesson>
    ) {
        enum class TabType {
            SCHEDULED,
            COMPLETED,
        }

        data class Lesson(
            val dateString: String = "",
            val exercises: List<String> = emptyList(),
        )
    }
}
