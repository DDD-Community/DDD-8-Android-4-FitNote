package com.dogandpigs.fitnote.presentation.setting

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.core.TokenManager
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
) : BaseViewModel<SettingUiState>() {
    override fun createInitialState(): SettingUiState = SettingUiState()

    fun initialize() {
        initializeTrainerInfo()
    }

    private fun initializeTrainerInfo() {
        viewModelScope.launch {
            runCatching {
                memberRepository.getMemberList()
            }.onSuccess {
                val trainerInfo = it?.trainerInfo
                checkNotNull(trainerInfo)

                setState {
                    copy(
                        name = trainerInfo.userName,
                        email = trainerInfo.userEmail,
                    )
                }
            }
        }
    }

    fun logout() {
        TokenManager.clearAccessToken()
        setState {
            copy(
                logout = true,
            )
        }
    }

    fun withdrawal() {
        viewModelScope.launch {
            runCatching {
                memberRepository.deleteTrainer()
            }.onSuccess {
                TokenManager.clearAccessToken()
                setState {
                    copy(
                        withdrawal = true,
                    )
                }
                Log.d("aa12", "onSuccess")
            }.onFailure {
                Log.d("aa12", "onFailure")
            }
        }
    }

}
