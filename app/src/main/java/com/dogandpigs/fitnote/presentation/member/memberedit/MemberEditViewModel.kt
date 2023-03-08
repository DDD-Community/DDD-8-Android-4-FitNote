package com.dogandpigs.fitnote.presentation.member.memberedit

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.data.source.remote.request.MemberRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import com.dogandpigs.fitnote.presentation.util.formatDate
import com.dogandpigs.fitnote.presentation.util.trimTrailingZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberEditViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MemberUiState>() {
    override fun createInitialState(): MemberUiState = MemberUiState()

    fun initialize(memberId: Long) {
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
                        dateMillis = member.createDate.formatDate()?.time
                            ?: System.currentTimeMillis(),
                        height = "${trimTrailingZero(member.userHeight?.toString())}",
                        weight = "${trimTrailingZero(member.userWeight?.toString())}",
                        gender = getGender(member.userGender),
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

    private fun getGender(value: Int?): MemberUiState.Gender =
        when (value) {
            1 -> MemberUiState.Gender.MALE
            2 -> MemberUiState.Gender.FEMALE
            else -> MemberUiState.Gender.NONE
        }

    // TODO
    fun editMember() {
        currentState {
            viewModelScope.launch {
                runCatching {
                    check(name.isNotBlank()) { "이름을 입력해주세요." }
                    val userWeight = weight.toDoubleOrNull()
                    checkNotNull(userWeight) { "몸무게는 숫자만 입력가능합니다." }
                    val userHeight = height.toDoubleOrNull()
                    checkNotNull(userHeight) { "키는 숫자만 입력가능합니다." }

                    val member = MemberRequest(
                        userName = name,
                        userWeight = userWeight,
                        userHeight = userHeight,
                        userGender = gender.value,
                    )

                    memberRepository.editMember(member)
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
