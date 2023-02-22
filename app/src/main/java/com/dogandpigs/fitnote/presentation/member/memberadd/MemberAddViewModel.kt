package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.data.source.remote.request.MemberRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberAddViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : BaseViewModel<MemberAddUiState>() {
    override fun createInitialState(): MemberAddUiState = MemberAddUiState()

    fun addMember() {
        currentState {
            val member = MemberRequest(
                userName = name,
                userWeight = weight.toDouble(),
                userHeight = height.toDouble(),
                userGender = gender.value,
            )

            viewModelScope.launch {
                memberRepository.addMember(member).let { result ->
                    if (result) {
                        setState {
                            copy(isAddSuccess = true)
                        }
                    }
                }
            }
        }
    }
    
    fun setName(name: String) = setState {
        copy(name = name)
    }
    
    fun setHeight(height: Int) = setState {
        copy(height = height)
    }
    
    fun setWeight(weight: Int) = setState {
        copy(weight = weight)
    }
    
    fun setGender(gender: MemberAddUiState.Gender) = setState {
        copy(gender = gender)
    }
}
