package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonListViewModel @Inject constructor(

) : ViewModel() {
    val uiState: MutableStateFlow<MemberLessonListUiState> =
        MutableStateFlow(MemberLessonListUiState())
}
