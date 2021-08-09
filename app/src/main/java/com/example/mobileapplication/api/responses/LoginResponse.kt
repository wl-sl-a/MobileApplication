package com.example.mobileapplication.api.responses
import com.google.gson.annotations.SerializedName
import java.util.*

data class LoginResponse (
    @SerializedName("expiration")
    var expiration: Date,

    @SerializedName("token")
    var token: String
)