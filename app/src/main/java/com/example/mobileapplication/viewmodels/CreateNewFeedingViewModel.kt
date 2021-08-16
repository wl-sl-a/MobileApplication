package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.repositories.AquariumRepository
import com.example.mobileapplication.repositories.FeedingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewFeedingViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Aquarium>>
    var repository: AquariumRepository
    var repositoryFeeding: FeedingRepository
    var createNewFeedingLiveData: MutableLiveData<FeedingResponse?>

    init {
        recyclerListData = MutableLiveData()
        repository = AquariumRepository()
        repositoryFeeding = FeedingRepository()
        createNewFeedingLiveData = MutableLiveData()
    }

    fun getAquariumListObserverable() : MutableLiveData<MutableList<Aquarium>> {
        return recyclerListData
    }

    fun getAquariumsList(context: Context) {
        repository.getAquariumsList(context)
            .enqueue(object : Callback<MutableList<Aquarium>> {
                override fun onFailure(call: Call<MutableList<Aquarium>>, t: Throwable) {
                    recyclerListData.postValue(null)
                }
                override fun onResponse(call: Call<MutableList<Aquarium>>, response: Response<MutableList<Aquarium>>) {
                    if(response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }

    fun getCreateNewFeedingObservable(): MutableLiveData<FeedingResponse?> {
        return  createNewFeedingLiveData
    }

    fun createAquarium(context: Context, request: FeedingRequest) {
        repositoryFeeding.createFeeding(context, request).enqueue(object : Callback<FeedingResponse?> {
            override fun onFailure(call: Call<FeedingResponse?>, t: Throwable) {
                createNewFeedingLiveData.postValue(null)
            }
            override fun onResponse(call: Call<FeedingResponse?>, response: Response<FeedingResponse?>) {
                if(response.isSuccessful) {
                    createNewFeedingLiveData.postValue(response.body())
                } else {
                    createNewFeedingLiveData.postValue(null)
                }
            }
        })
    }
}