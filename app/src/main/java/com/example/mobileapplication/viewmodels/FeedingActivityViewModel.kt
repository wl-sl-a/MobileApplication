package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.models.Feeding
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.repositories.FeedingRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedingActivityViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Feeding>>
    var deletedFeedingLiveData: MutableLiveData<FeedingResponse?>
    var repository: FeedingRepository

    init {
        recyclerListData = MutableLiveData()
        deletedFeedingLiveData = MutableLiveData()
        repository = FeedingRepository()
    }

    fun getFeedingListObserverable() : MutableLiveData<MutableList<Feeding>> {
        return recyclerListData
    }

    fun getFeedingsList(context: Context) {
        repository.getFeedingsList(context)
            .enqueue(object : Callback<MutableList<Feeding>> {
                override fun onFailure(call: Call<MutableList<Feeding>>, t: Throwable) {
                    recyclerListData.postValue(null)
                }
                override fun onResponse(call: Call<MutableList<Feeding>>, response: Response<MutableList<Feeding>>) {
                    if(response.isSuccessful) {
                        recyclerListData.postValue(response.body())
                    } else {
                        recyclerListData.postValue(null)
                    }
                }
            })
    }
}