package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.repositories.AquariumRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditAquariumViewModel: ViewModel() {
    var editAquariumLiveData: MutableLiveData<AquariumResponse?>
    var repository: AquariumRepository

    init {
        editAquariumLiveData = MutableLiveData()
        repository = AquariumRepository()
    }

    fun editAquariumObservable(): MutableLiveData<AquariumResponse?> {
        return editAquariumLiveData
    }

    fun editAquarium(context: Context, aquariumId: Int, request: AquariumRequest) {
        repository.editAquarium(context, aquariumId, request).enqueue(object : Callback<AquariumResponse?> {
            override fun onFailure(call: Call<AquariumResponse?>, t: Throwable) {
                editAquariumLiveData.postValue(null)
            }
            override fun onResponse(call: Call<AquariumResponse?>, response: Response<AquariumResponse?>) {
                if(response.isSuccessful) {
                    editAquariumLiveData.postValue(response.body())
                } else {
                    editAquariumLiveData.postValue(null)
                }
            }
        })
    }
}