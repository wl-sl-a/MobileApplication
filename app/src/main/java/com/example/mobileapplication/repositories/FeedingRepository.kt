package com.example.mobileapplication.repositories

import android.content.Context
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.models.Feeding
import com.example.mobileapplication.api.requests.CalculateDoseRequest
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.responses.FeedingResponse
import retrofit2.Call

class FeedingRepository {
    var apiClient: ApiClient;

    init{
        apiClient = ApiClient()
    }

    fun createFeeding(context: Context, request: FeedingRequest): Call<FeedingResponse> {
        return apiClient.getApiService(context).addFeeding(request)
    }

    fun getFeedingsList(context: Context): Call<MutableList<Feeding>> {
        return apiClient.getApiService(context).fetchFeedings()
    }

    fun deleteFeeding(context: Context, feedingId: Int): Call<FeedingResponse> {
        return apiClient.getApiService(context).deleteFeeding(feedingId)
    }

    fun editFeeding(context: Context, feedingId: Int, request: FeedingRequest): Call<FeedingResponse> {
        return apiClient.getApiService(context).editFeeding(feedingId, request)
    }

    fun calculateDose(context: Context, request: CalculateDoseRequest): Call<Double>{
        return apiClient.getApiService(context).calculateDose(request)
    }
}