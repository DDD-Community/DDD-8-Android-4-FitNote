package com.dogandpigs.fitnote.presentation.memberlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberListViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(MemberListUiState())
        private set
}
