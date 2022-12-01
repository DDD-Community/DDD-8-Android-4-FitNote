package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class JoinRequestDTO(
    @SerializedName("name")
    var name: String? = null,
    
    @SerializedName("email")
    var email: String? = null,
    
    @SerializedName("password1")
    var firstPassword: String? = null,
    
    @SerializedName("password2")
    var verifyPassword: String? = null
)