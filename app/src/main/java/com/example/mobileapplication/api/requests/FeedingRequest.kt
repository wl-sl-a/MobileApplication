package com.example.mobileapplication.api.requests

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class FeedingRequest(
    @SerializedName("kindOfFeed")
    var kindOfFeed: String?,

    @SerializedName("dose")
    var dose: Int?,

    @SerializedName("aquariumId")
    var aquariumId: Int?,

    @SerializedName("date")
    var date: String?,

    @SerializedName("time")
    var time: String?
)
