package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.repositories.AquariumRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Aquarium>>
    var repository: AquariumRepository

    init {
        recyclerListData = MutableLiveData()
        repository = AquariumRepository()
    }

    fun getAquariumListObserverable() : MutableLiveData<MutableList<Aquarium>> {
        return recyclerListData
    }

    fun getUsersList(context: Context) {
        repository.getUsersList(context)
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