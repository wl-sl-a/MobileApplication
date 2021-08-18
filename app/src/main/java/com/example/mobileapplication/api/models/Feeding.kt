package com.example.mobileapplication.api.models
import com.google.gson.annotations.SerializedName
import java.util.*

data class Feeding(
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("kindOfFeed")
    var kindOfFeed: String? = null,

    @SerializedName("dose")
    var dose: Int? = null,

    @SerializedName("aquariumId")
    var aquariumId: Int? = null,

    @SerializedName("date")
    var date: String? = null,

    @SerializedName("time")
    var time: String? = null,

    @SerializedName("nameCompany")
    var nameCompany: String? = null
)
