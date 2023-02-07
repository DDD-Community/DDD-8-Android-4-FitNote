package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonResponse (
    @SerializedName("getMemberInfo")
    val memberInfo: Description = Description(),
    
    @SerializedName("getLessonInfo")
    val lessonIngo: Description = Description()
) {
    data class Description(
        @SerializedName("user_id")
        val userId: Int = 0,
        
        @SerializedName("user_name")
        val userName: String = "",
        
        @SerializedName("user_email")
        val userEmail: String = "",
        
        @SerializedName("user_type")
        val userType: String = "",
        
        @SerializedName("trainer_group")
        val trainerGroup: Int = 0,
        
        @SerializedName("user_height")
        val userHeight: Int = 0,
        
        @SerializedName("user_status")
        val userStatus: Int = 0,
        
        @SerializedName("user_view")
        val userView: Int = 0,
        
        @SerializedName("user_gender")
        val userGender: Int = 0,
        
        @SerializedName("create_date")
        val createDate: String = "",
        
        @SerializedName("update_date")
        val updateDate: String = "",
        
        @SerializedName("id")
        val id: Int = 0,
        
        @SerializedName("lessons_date")
        val lessonsDate: String = "",
        
        @SerializedName("lessonsName")
        val lessonsName: String = ""
    )
}