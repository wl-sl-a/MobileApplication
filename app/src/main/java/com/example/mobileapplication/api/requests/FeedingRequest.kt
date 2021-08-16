package com.example.mobileapplication.api.requests

import com.google.gson.annotations.SerializedName
import java.util.*

data class FeedingRequest(
    @SerializedName("kindOfFeed")
    var kindOfFeed: String? = null,

    @SerializedName("dose")
    var dose: Int? = null,

    @SerializedName("aquariumId")
    var aquariumId: Int? = null,

    @SerializedName("dataTime")
    var dateTime: Date? = null
)
