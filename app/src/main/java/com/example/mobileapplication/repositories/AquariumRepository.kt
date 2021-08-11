package com.example.mobileapplication.repositories

import android.content.Context
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import retrofit2.Call

class AquariumRepository {
    var apiClient: ApiClient;

    init{
        apiClient = ApiClient()
    }

    fun createAquarium(context: Context, request: AquariumRequest):Call<AquariumResponse>{
        return apiClient.getApiService(context).addAquarium(request)
    }

    fun getAquariumsList(context: Context):Call<MutableList<Aquarium>>{
        return apiClient.getApiService(context).fetchAquariums();
    }

}