package com.example.mobileapplication.api

import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.requests.LoginRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.api.responses.LoginResponse
import com.example.mobileapplication.utils.Constants
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.AQUARIUMS_URL)
    fun fetchPosts(): Call<MutableList<Aquarium>>

    @POST(Constants.AQUARIUMS_URL)
    fun addAquarium(@Body request: AquariumRequest): Call<AquariumResponse>
}