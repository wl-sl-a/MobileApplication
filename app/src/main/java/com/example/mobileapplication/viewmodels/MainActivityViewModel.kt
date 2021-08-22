package com.example.mobileapplication.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.repositories.AquariumRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    var recyclerListData: MutableLiveData<MutableList<Aquarium>>
    var deletedAquariumLiveData: MutableLiveData<AquariumResponse?>
    var repository: AquariumRepository

    init {
        recyclerListData = MutableLiveData()
        deletedAquariumLiveData = MutableLiveData()
        repository = AquariumRepository()
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

    fun search(context: Context, param: String) {
        repository.searchAquarium(context, param)
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

    fun deleteAquariumObserverable(): MutableLiveData<AquariumResponse?>{
        return deletedAquariumLiveData
    }

    fun deleteAquarium(context: Context, aquariumId: Int){
       repository.deleteAquarium(context, aquariumId)
        .enqueue(object : Callback<AquariumResponse?> {
            override fun onFailure(call: Call<AquariumResponse?>, t: Throwable) {
                deletedAquariumLiveData.postValue(null)
            }

            override fun onResponse(call: Call<AquariumResponse?>, response: Response<AquariumResponse?>) {
                if(response.isSuccessful) {
                    deletedAquariumLiveData.postValue(response.body())
                } else {
                    deletedAquariumLiveData.postValue(null)
                }
            }
        })
    }
}