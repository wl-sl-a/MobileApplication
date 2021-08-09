package com.example.mobileapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.R
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.viewmodels.CreateNewAquariumViewModel
import kotlinx.android.synthetic.main.activity_create_new_aquarium.*

class CreateNewAquariumActivity : AppCompatActivity() {
    lateinit var viewModel: CreateNewAquariumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_aquarium)
        viewModel = ViewModelProvider(this).get(CreateNewAquariumViewModel::class.java)
        createAquariumObservable()
        createButton.setOnClickListener {
            createAquarium()
        }
    }

    private fun createAquariumObservable() {
        viewModel.getCreateNewAquariumObservable().observe(this, Observer <AquariumResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateNewAquariumActivity, "Failed to create/update new user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateNewAquariumActivity, "Successfully created/updated user...", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }

    fun createAquarium(){
        val request = AquariumRequest(editTextManufacturer.text.toString(), editTextVolume.text.toString().toInt(),
            editTextLength.text.toString().toInt(), editTextWidth.text.toString().toInt(), editTextHeight.text.toString().toInt())
        viewModel.createAquarium(applicationContext, request)
    }
}