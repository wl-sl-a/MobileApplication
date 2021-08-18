package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.repositories.AquariumRepository
import com.example.mobileapplication.repositories.FeedingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditFeedingViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Aquarium>>
    var editFeedingLiveData: MutableLiveData<FeedingResponse?>
    var repository: FeedingRepository
    var repositoryAqua: AquariumRepository

    init {
        recyclerListData = MutableLiveData()
        editFeedingLiveData = MutableLiveData()
        repositoryAqua = AquariumRepository()
        repository = FeedingRepository()
    }

    fun editFeedingObservable(): MutableLiveData<FeedingResponse?> {
        return editFeedingLiveData
    }

    fun editFeeding(context: Context, feedingId: Int, request: FeedingRequest) {
        repository.editFeeding(context, feedingId, request).enqueue(object :
            Callback<FeedingResponse?> {
            override fun onFailure(call: Call<FeedingResponse?>, t: Throwable) {
                editFeedingLiveData.postValue(null)
            }
            override fun onResponse(call: Call<FeedingResponse?>, response: Response<FeedingResponse?>) {
                if(response.isSuccessful) {
                    editFeedingLiveData.postValue(response.body())
                } else {
                    editFeedingLiveData.postValue(null)
                }
            }
        })
    }

    fun getAquariumListObserverable() : MutableLiveData<MutableList<Aquarium>> {
        return recyclerListData
    }

    fun getAquariumsList(context: Context) {
        repositoryAqua.getAquariumsList(context)
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
}