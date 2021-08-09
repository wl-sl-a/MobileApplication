package com.example.mobileapplication.api.models
import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    var id: String,

    @SerializedName("user_name")
    var userName: String,

    @SerializedName("email")
    var email: String
    )