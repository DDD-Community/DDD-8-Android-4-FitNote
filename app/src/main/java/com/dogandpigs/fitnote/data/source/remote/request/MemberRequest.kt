package com.dogandpigs.fitnote.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class MemberRequest(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_height")
    val userHeight: Double?,
    @SerializedName("user_weight")
    val userWeight: Double?,
    @SerializedName("user_gender")
    val userGender: Int?,
)
