package com.example.mobileapplication.api.responses

import com.google.gson.annotations.SerializedName
import java.util.*

data class FeedingResponse(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("kindOfFeed")
    var kindOfFeed: String? = null,

    @SerializedName("dose")
    var dose: Int? = null,

    @SerializedName("aquariumId")
    var aquariumId: Int? = null,

    @SerializedName("dataTime")
    var dateTime: Date? = null,

    @SerializedName("nameCompany")
    var nameCompany: String? = null
)
