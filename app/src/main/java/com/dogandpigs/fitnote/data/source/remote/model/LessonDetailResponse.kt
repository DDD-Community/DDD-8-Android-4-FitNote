package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonDetailResponse(
    @SerializedName("getMemberInfo")
    val memberInfo: Description,

    @SerializedName("getLessonInfo")
    val lessonInfo: List<List<LessonDescription>>
) {
    data class Description(
        @SerializedName("user_id")
        val userId: Int = 0,

        @SerializedName("user_name")
        val userName: String = "",

        @SerializedName("user_email")
        val userEmail: String = "",

        @SerializedName("user_type")
        val userType: Int = 0,

        @SerializedName("trainer_group")
        val trainerGroup: Int = 0,

        @SerializedName("user_height")
        val userHeight: Double = 0.0,

        @SerializedName("user_weight")
        val userWeight: Double = 0.0,

        @SerializedName("user_status")
        val userStatus: Int = 0,

        @SerializedName("user_view")
        val userView: Int = 0,

        @SerializedName("user_gender")
        val userGender: Int = 0,

        @SerializedName("create_date")
        val createDate: String = "",

        @SerializedName("update_date")
        val updateDate: String? = "",

        @SerializedName("id")
        val id: Int = 0,
    )

    data class LessonDescription(
        @SerializedName("user_id")
        val userId: Int = 0,

        @SerializedName("name")
        val name: String = "",

        @SerializedName("weight")
        val weight: String = "",

        @SerializedName("count")
        val count: Int = 0,

        @SerializedName("set")
        val set: Int = 0,

        @SerializedName("completion")
        val completion: Int = 0,

        @SerializedName("total_completion")
        val totalCompletion: Int = 0,

        @SerializedName("view_yn")
        val viewYn: Int = 0,

        @SerializedName("start_date")
        val startDate: Int = 0,

        @SerializedName("create_date")
        val createDate: String = "",

        @SerializedName("lesson_id")
        val lessonId: Int = 0,
    )
}
