package com.example.mobileapplication.api.requests

import com.google.gson.annotations.SerializedName

data class AquariumRequest(
    @SerializedName("manufacturer")
    var manufacturer: String? = null,

    @SerializedName("volume")
    var volume: Int? = null,

    @SerializedName("length")
    var length: Int? = null,

    @SerializedName("width")
    var width: Int? = null,

    @SerializedName("height")
    var height: Int? = null
)
