package com.dogandpigs.fitnote.presentation.member.memberlist

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberListViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MemberListUiState>() {
    override fun createInitialState(): MemberListUiState = MemberListUiState()

    fun initialize() {
        getMemberList()
    }

    private fun getMemberList() {
        viewModelScope.launch {
            memberRepository.getMemberList()?.let { trainerInfo ->
                setState {
                    copy(
                        myName = trainerInfo.trainerInfo?.userName ?: "",
                        userList = trainerInfo.memberList.map {
                            it.toPresentation()
                        },
                    )
                }
            }
        }
    }
}
