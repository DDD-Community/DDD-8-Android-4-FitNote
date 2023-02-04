package com.dogandpigs.fitnote.presentation.member.memberdetail

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.util.formatDate
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
            kotlin.runCatching {
                memberRepository.getMemberList()
            }.onSuccess {
                val member = it?.memberList?.first { member ->
                    member.id == memberId
                }

                member?.also {
                    setState {
                        copy(
                            name = it.userName ?: "",
                            date = it.createDate?.formatDate() ?: "",
                            gender = formatGender(it.userGender),
                            height = "${trimTrailingZero(it.userHeight?.toString())}cm",
                            weight = "${trimTrailingZero(it.userWeight?.toString())}kg",
                        )
                    }
                } ?: return@onSuccess // TODO member is NULL
            }.onFailure {
                // TODO 실패할 경우
            }
        }
    }
}
