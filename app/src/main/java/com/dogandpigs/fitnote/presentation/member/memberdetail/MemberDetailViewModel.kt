package com.dogandpigs.fitnote.presentation.member.memberdetail

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.util.formatDateString
import com.dogandpigs.fitnote.presentation.util.formatGender
import com.dogandpigs.fitnote.presentation.util.trimTrailingZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberDetailViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MemberDetailUiState>() {
    override fun createInitialState(): MemberDetailUiState = MemberDetailUiState()

    fun initialize(memberId: Int) {
        viewModelScope.launch {
            runCatching {
                val response = memberRepository.getMemberList()
                val member = response?.memberList?.first { member ->
                    member.id == memberId
                }
                checkNotNull(member) { "member is null" }
                setState {
                    copy(
                        name = member.userName,
                        date = member.createDate.formatDateString() ?: "",
                        gender = formatGender(member.userGender),
                        height = "${trimTrailingZero(member.userHeight?.toString())}cm",
                        weight = "${trimTrailingZero(member.userWeight?.toString())}kg",
                    )
                }
            }.onSuccess {
                // TODO
                Log.d("develop", "onSuccess")
            }.onFailure {
                // TODO
                Log.d("develop", "onFailure")
            }
        }
    }
}
