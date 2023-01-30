package com.dogandpigs.fitnote.data.repository

import com.dogandpigs.fitnote.data.source.remote.api.MemberApi
import com.dogandpigs.fitnote.data.source.remote.model.Member
import com.dogandpigs.fitnote.data.source.remote.model.TrainerInfo
import javax.inject.Inject

class MemberRepository @Inject constructor(
    private val memberApi: MemberApi
) {
    suspend fun getMemberList(): TrainerInfo? {
        memberApi.getMemberList().run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }
    
    suspend fun addMember(member: Member): Boolean {
        memberApi.addMember(member).run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return false
            }
            return true
        }
    }
}