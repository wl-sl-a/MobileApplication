package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.models.Feeding
import com.example.mobileapplication.api.requests.CalculateDoseRequest
import com.example.mobileapplication.repositories.FeedingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalculateDoseViewModel: ViewModel() {
    var repository: FeedingRepository
    var result: MutableLiveData<Double>

    init{
        repository = FeedingRepository()
        result = MutableLiveData()
    }

    fun calculateDoseObserverable() : MutableLiveData<Double> {
        return result
    }

    fun calculateDose(context: Context, request: CalculateDoseRequest) {
        repository.calculateDose(context, request)
            .enqueue(object : Callback<Double> {
                override fun onFailure(call: Call<Double>, t: Throwable) {
                    result.postValue(null)
                }
                override fun onResponse(call: Call<Double>, response: Response<Double>) {
                    if(response.isSuccessful) {
                        result.postValue(response.body())
                    } else {
                        result.postValue(null)
                    }
                }
            })
    }
}