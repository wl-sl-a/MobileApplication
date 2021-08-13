package com.example.mobileapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.R
import com.example.mobileapplication.api.requests.AquariumRequest
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.viewmodels.EditAquariumViewModel
import kotlinx.android.synthetic.main.activity_create_new_aquarium.*
import kotlinx.android.synthetic.main.activity_edit_aquarium.*


class EditAquariumActivity : AppCompatActivity() {
    lateinit var viewModel: EditAquariumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_aquarium)
        var id = getIntent().getExtras()?.getString("id");
        var manufacturer = getIntent().getExtras()?.getString("manufacturer");

        val editTextId = findViewById<View>(R.id.editId) as EditText
        editTextId.setText(id)
        val editTextManufacturer = findViewById<View>(R.id.editManufacturer) as EditText
        editTextManufacturer.setText(manufacturer)
        viewModel = ViewModelProvider(this).get(EditAquariumViewModel::class.java)

        editAquariumObservable()
        editButton.setOnClickListener {
            editAquarium()
        }
    }

    private fun editAquariumObservable() {
        viewModel.editAquariumObservable().observe(this, Observer <AquariumResponse?>{
            if(it == null) {
                Toast.makeText(this@EditAquariumActivity, "Failed to update new aquarium...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@EditAquariumActivity, "Successfully updated aquarium...", Toast.LENGTH_LONG).show()
                val refresh = Intent(this, MainActivity::class.java)
                startActivity(refresh)
                finish()
            }
        })
    }

    fun editAquarium(){
        val request = AquariumRequest(editManufacturer.text.toString(), editVolume.text.toString().toInt(),
            editLength.text.toString().toInt(), editWidth.text.toString().toInt(), editHeight.text.toString().toInt())
        viewModel.editAquarium(applicationContext, editId.text.toString().toInt(), request)
    }
}