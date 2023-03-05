package com.dogandpigs.fitnote.presentation.setting

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    private val memberRepository: MemberRepository
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
}
