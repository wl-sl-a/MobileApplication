package com.example.mobileapplication.api.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class FeedingResponse(
    @SerializedName("id")
    var id: Int?,

    @SerializedName("kindOfFeed")
    var kindOfFeed: String?,

    @SerializedName("dose")
    var dose: Int?,

    @SerializedName("aquariumId")
    var aquariumId: Int?,

    @SerializedName("date")
    var date: String?,

    @SerializedName("time")
    var time: String?,

    @SerializedName("nameCompany")
    var nameCompany: String?
)
