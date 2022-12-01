package com.dogandpigs.fitnote.data.source.remote.model

import com.dogandpigs.fitnote.core.ResCode
import com.google.gson.annotations.SerializedName

/**
 * 후에 api response 확정되면 수정
 * */
data class ResBase<T>(
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String,
    @SerializedName("debug") val debug: Debug?,
    @SerializedName("data") val data: T?
) {

    fun isSuccess(): Boolean {
        return code == ResCode.SUCCESS
    }
}

data class Debug(
    @SerializedName("exception") val exception: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("file") val file: String,
    @SerializedName("line") val line: Int
)