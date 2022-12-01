package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LoginRequestDTO(
    @SerializedName("email")
    var email: String? = null,
    
    @SerializedName("password")
    var password: String? = null
)