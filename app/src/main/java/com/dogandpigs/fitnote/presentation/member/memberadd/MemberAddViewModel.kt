package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.data.source.remote.request.MemberRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberAddViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MemberUiState>() {
    override fun createInitialState(): MemberUiState = MemberUiState()

    fun addMember() {
        currentState {
            viewModelScope.launch {
                runCatching {
                    check(name.isNotBlank()) { "이름을 입력해주세요." }
                    val userWeight = weight.ifEmpty { "0" }.toDoubleOrNull()
                    checkNotNull(userWeight) { "몸무게는 숫자만 입력가능합니다." }
                    val userHeight = height.ifEmpty { "0" }.toDoubleOrNull()
                    checkNotNull(userHeight) { "키는 숫자만 입력가능합니다." }

                    val member = MemberRequest(
                        userName = name,
                        userWeight = userWeight,
                        userHeight = userHeight,
                        userGender = gender.value,
                    )

                    memberRepository.addMember(member)
                }.onSuccess {
                    setState {
                        copy(isNext = true)
                    }
                }.onFailure {
                    setState {
                        copy(
                            toast = it.message ?: ""
                        )
                    }
                }
            }
        }
    }

    fun setName(name: String) = setState {
        copy(name = name)
    }

    fun setHeight(height: String) = setState {
        copy(height = height)
    }

    fun setWeight(weight: String) = setState {
        copy(weight = weight)
    }

    fun setGender(gender: MemberUiState.Gender) = setState {
        copy(gender = gender)
    }

    fun setDateMillis(dateMillis: Long?) = setState {
        checkNotNull(dateMillis)
        copy(dateMillis = dateMillis)
    }
}
