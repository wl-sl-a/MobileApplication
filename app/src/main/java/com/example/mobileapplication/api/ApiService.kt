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
    fun fetchAquariums(): Call<MutableList<Aquarium>>

    @GET(Constants.AQUARIUMS_URL+"/{id}")
    fun getAquarium(@Path("id") id: Int): Call<AquariumResponse>

    @POST(Constants.AQUARIUMS_URL)
    fun addAquarium(@Body request: AquariumRequest): Call<AquariumResponse>

    @PUT(Constants.AQUARIUMS_URL+"/{id}")
    fun editAquarium(@Path("id") id: Int, @Body request: AquariumRequest): Call<AquariumResponse>

    @DELETE(Constants.AQUARIUMS_URL+"/{id}")
    fun deleteAquarium(@Path("id") id: Int): Call<AquariumResponse>
}