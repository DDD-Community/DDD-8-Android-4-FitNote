package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("access_token")
    var accessToken: String? = null,
    
    @SerializedName("refresh_token")
    var refreshToken: String? = null,
    
    @SerializedName("user")
    var user: User? = null
)

data class User(
    @SerializedName("email")
    var email: String? = null,
    
    @SerializedName("first_name")
    var firstName: String? = null,
    
    @SerializedName("last_name")
    var lastName: String? = null
)