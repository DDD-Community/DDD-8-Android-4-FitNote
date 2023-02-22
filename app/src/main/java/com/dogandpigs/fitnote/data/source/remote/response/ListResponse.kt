package com.dogandpigs.fitnote.data.source.remote.response

import com.dogandpigs.fitnote.data.source.remote.model.Info
import com.google.gson.annotations.SerializedName

data class ListResponse(
    @SerializedName("getTrainerInfo")
    var trainerInfo: Info,

    @SerializedName("getMemberCount")
    var memberCount: Int,

    @SerializedName("getMemberList")
    var memberList: List<Info>,
)
