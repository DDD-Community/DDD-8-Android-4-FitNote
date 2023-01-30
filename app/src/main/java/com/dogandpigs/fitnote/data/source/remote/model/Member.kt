package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class TrainerInfo(
    @SerializedName("getTrainerInfo")
    var trainerInfo: Member? = null,
    
    @SerializedName("getMemberCount")
    var memberCount: Int? = null,
    
    @SerializedName("getMemberList")
    var memberList: List<Member> = mutableListOf()

)

data class Member(
    @SerializedName("user_id")
    var userId: String? = null,
    
    @SerializedName("user_name")
    var userName: String? = null,
    
    @SerializedName("user_type")
    var userType: Int? = null,
    
    @SerializedName("trainer_group")
    var trainerGroup: String? = null,
    
    @SerializedName("user_height")
    var userHeight: Double? = null,
    
    @SerializedName("user_weight")
    var userWeight: Double? = null,
    
    @SerializedName("user_status")
    var userStatus: Int? = null,
    
    @SerializedName("user_view")
    var userView: Int? = null,
    
    @SerializedName("user_gender")
    var userGender: Int? = null,
    
    @SerializedName("create_date")
    var createDate: String? = null,
    
    @SerializedName("update_date")
    var updateDate: String? = null
)
