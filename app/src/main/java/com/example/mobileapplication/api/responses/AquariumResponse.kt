package com.example.mobileapplication.api.responses
import com.google.gson.annotations.SerializedName
import com.example.mobileapplication.api.models.Aquarium

data class AquariumResponse (
    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("manufacturer")
    var manufacturer: String? = null,

    @SerializedName("volume")
    var volume: Int? = null,

    @SerializedName("length")
    var length: Int? = null,

    @SerializedName("width")
    var width: Int? = null,

    @SerializedName("height")
    var height: Int? = null,

    @SerializedName("nameCompany")
    var nameCompany: String? = null
)