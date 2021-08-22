package com.example.mobileapplication.api

import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.models.Feeding
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.requests.CalculateDoseRequest
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.requests.LoginRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.api.responses.FeedingResponse
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

    @GET(Constants.AQUARIUMS_URL+"/search/{param}")
    fun searchAquarium(@Path("param") param: String): Call<MutableList<Aquarium>>

    @POST(Constants.AQUARIUMS_URL)
    fun addAquarium(@Body request: AquariumRequest): Call<AquariumResponse>

    @PUT(Constants.AQUARIUMS_URL+"/{id}")
    fun editAquarium(@Path("id") id: Int, @Body request: AquariumRequest): Call<AquariumResponse>

    @DELETE(Constants.AQUARIUMS_URL+"/{id}")
    fun deleteAquarium(@Path("id") id: Int): Call<AquariumResponse>

    @GET(Constants.FEEDINGS_URL)
    fun fetchFeedings(): Call<MutableList<Feeding>>

    @GET(Constants.FEEDINGS_URL+"/{id}")
    fun getFeeding(@Path("id") id: Int): Call<FeedingResponse>

    @POST(Constants.FEEDINGS_URL)
    fun addFeeding(@Body request: FeedingRequest): Call<FeedingResponse>

    @PUT(Constants.FEEDINGS_URL+"/{id}")
    fun editFeeding(@Path("id") id: Int, @Body request: FeedingRequest): Call<FeedingResponse>

    @DELETE(Constants.FEEDINGS_URL+"/{id}")
    fun deleteFeeding(@Path("id") id: Int): Call<FeedingResponse>

    @POST(Constants.FEEDINGS_URL+"/calculate")
    fun calculateDose(@Body request: CalculateDoseRequest): Call<Double>
}