package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("id")
    val id: Int,

    @SerializedName("user_id")
    val userId: Int?,

    @SerializedName("user_name")
    val userName: String,

    @SerializedName("user_email")
    val userEmail: String,

    @SerializedName("user_type")
    val userType: Int?,

    @SerializedName("trainer_group")
    val trainerGroup: Int,

    @SerializedName("user_weight")
    val userWeight: Double?,

    @SerializedName("user_height")
    val userHeight: Double?,

    @SerializedName("user_status")
    val userStatus: Int,

    @SerializedName("user_view")
    val userView: Int,

    @SerializedName("user_gender")
    val userGender: Int?,

    @SerializedName("create_date")
    val createDate: String,

    @SerializedName("update_date")
    val updateDate: String?,
)
