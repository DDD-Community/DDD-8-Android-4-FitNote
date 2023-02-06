package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

internal data class MemberLessonListUiState(
    val memberId: Int = 0,
    val userName: String = "",
    val scheduledLessonTab: Tab = Tab(Tab.TabType.SCHEDULED),
    val completedLessonTab: Tab = Tab(Tab.TabType.COMPLETED),
) {
    data class Tab(
        val tabType: TabType,
        val lessons: List<Lesson> = emptyList(),
    ) {
        enum class TabType(
            val title: String,
            val message: String,
        ) {
            SCHEDULED("예정된 수업", "\n 수업을 추가해보세요!"),
            COMPLETED("완료한 수업", ""),
        }

        data class Lesson(
            val dateString: String = "",
            val exercises: List<String> = emptyList(),
        )
    }
}
