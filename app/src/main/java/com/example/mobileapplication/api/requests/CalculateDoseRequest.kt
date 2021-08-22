package com.example.mobileapplication.api.requests

import com.google.gson.annotations.SerializedName

data class CalculateDoseRequest (
    @SerializedName("param")
    var dose: Int?,

    @SerializedName("count")
    var count: Int?,

    @SerializedName("weight")
    var weight: Int?
)