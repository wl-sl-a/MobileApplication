package com.example.mobileapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.viewmodels.CreateNewFeedingViewModel
import kotlinx.android.synthetic.main.activity_create_new_aquarium.*
import kotlinx.android.synthetic.main.activity_create_new_feeding.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class CreateNewFeedingActivity : AppCompatActivity() {
    lateinit var viewModel: CreateNewFeedingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_feeding)
        getAquaIdList()

        viewModel = ViewModelProvider(this).get(CreateNewFeedingViewModel::class.java)
        createFeedingObservable()
        createFeedingButton.setOnClickListener {
            createFeeding()
        }
    }

    private fun createFeedingObservable() {
        viewModel.getCreateNewFeedingObservable().observe(this, Observer <FeedingResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateNewFeedingActivity, "Failed to create/update new aquarium...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateNewFeedingActivity, "Successfully created/updated aquarium...", Toast.LENGTH_LONG).show()
                val refresh = Intent(this, FeedingActivity::class.java)
                startActivity(refresh)
                finish()
            }
        })
    }

    fun createFeeding(){
        val request = FeedingRequest(editTextKind.text.toString(), editTextDose.text.toString().toInt(),
            editTextAquaId.selectedItem.toString().toInt(), Calendar.getInstance().getTime())
        viewModel.createAquarium(applicationContext, request)
    }

    fun getAquaIdList(){
        val spinner = findViewById<Spinner>(R.id.editTextAquaId)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        viewModel = ViewModelProvider(this).get(CreateNewFeedingViewModel::class.java)
        viewModel.getAquariumListObserverable().observe(this, Observer<MutableList<Aquarium>> {
            if(it == null) {
                Toast.makeText(this@CreateNewFeedingActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                for(item in it){
                    adapter.add(item.id.toString())
                    println("llll"+ item.id.toString())
                }
                spinner.adapter = adapter
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (parent != null) {
                            label_spiner.text = "Selected Aquarium: "+parent.getItemAtPosition(position).toString()
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(this@CreateNewFeedingActivity, "no result found...", Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
        viewModel.getAquariumsList(applicationContext)
    }
}
