package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

/**
 * 후에 api response 확정되면 수정
 * */
data class ResBase<T>(
    @SerializedName("result")
    val result: String,

    @SerializedName("status_code")
    val statusCode: String,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: T?,

    @SerializedName("debug")
    val debug: Debug?,
)

data class Debug(
    @SerializedName("exception") val exception: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("file") val file: String,
    @SerializedName("line") val line: Int
)
