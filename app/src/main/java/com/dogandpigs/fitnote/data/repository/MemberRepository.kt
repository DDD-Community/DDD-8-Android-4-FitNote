package com.dogandpigs.fitnote.data.repository

import android.util.Log
import com.dogandpigs.fitnote.data.source.remote.api.MemberApi
import com.dogandpigs.fitnote.data.source.remote.request.MemberRequest
import com.dogandpigs.fitnote.data.source.remote.response.LessonResponse
import com.dogandpigs.fitnote.data.source.remote.response.ListResponse
import com.dogandpigs.fitnote.data.util.handleResponseResBase
import javax.inject.Inject

class MemberRepository @Inject constructor(
    private val memberApi: MemberApi
) {
    suspend fun getMemberList(): ListResponse? {
        return handleResponseResBase(memberApi.getMemberList())
    }

    suspend fun addMember(memberRequest: MemberRequest) {
        handleResponseResBase(memberApi.addMember(memberRequest))
    }

    suspend fun editMember(memberRequest: MemberRequest) {
        handleResponseResBase(memberApi.putEditMember(memberRequest))
    }

    suspend fun getMemberInfo(): LessonResponse? {
        memberApi.getMemberInfo().run {
            if (!isSuccessful || body() == null || body()?.data == null) {
                return null
            }
            return body()?.data
        }
    }

    suspend fun deleteTrainer() {
        val tag = this::deleteTrainer.javaClass.name
        Log.d("aa12", "tag : $tag")
        memberApi.deleteTrainer().run {
            if (body()?.data == 1) {
                // Success
            } else {
                error("$tag is Failure")
            }
        }
    }
}
