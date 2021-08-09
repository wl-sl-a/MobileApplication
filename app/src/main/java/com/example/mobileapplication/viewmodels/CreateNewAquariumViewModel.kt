package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.repositories.AquariumRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateNewAquariumViewModel: ViewModel() {
    lateinit var createNewUserLiveData: MutableLiveData<AquariumResponse?>
    lateinit var loadUserData: MutableLiveData<AquariumResponse?>
    lateinit var deleteUserLiveData: MutableLiveData<AquariumResponse?>
    lateinit var repository: AquariumRepository

    init {
        createNewUserLiveData = MutableLiveData()
        loadUserData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
        repository = AquariumRepository()
    }

    fun getCreateNewAquariumObservable(): MutableLiveData<AquariumResponse?> {
        return  createNewUserLiveData
    }

    fun getDeleteAquariumObservable(): MutableLiveData<AquariumResponse?> {
        return  deleteUserLiveData
    }

    fun getLoadAquariumObservable(): MutableLiveData<AquariumResponse?> {
        return  loadUserData
    }

    fun createAquarium(context: Context, request: AquariumRequest) {
        repository.createAquarium(context, request).enqueue(object : Callback<AquariumResponse?> {
            override fun onFailure(call: Call<AquariumResponse?>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
            override fun onResponse(call: Call<AquariumResponse?>, response: Response<AquariumResponse?>) {
                if(response.isSuccessful) {
                    createNewUserLiveData.postValue(response.body())
                } else {
                    createNewUserLiveData.postValue(null)
                }
            }
        })
    }
}