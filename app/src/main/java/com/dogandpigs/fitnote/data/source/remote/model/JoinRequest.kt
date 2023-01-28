package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class JoinRequest(
    @SerializedName("fullname") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password1") val firstPassword: String,
    @SerializedName("password2") val verifyPassword: String,
)
