package com.example.mobileapplication.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.R
import com.example.mobileapplication.api.requests.CalculateDoseRequest
import com.example.mobileapplication.viewmodels.CalculateDoseViewModel
import kotlinx.android.synthetic.main.activity_calculate_dose.*
import kotlinx.android.synthetic.main.activity_menu.*


class CalculateDoseActivity : AppCompatActivity() {
    lateinit var viewModel: CalculateDoseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_dose)

        viewModel = ViewModelProvider(this).get(CalculateDoseViewModel::class.java)
        val spinner = findViewById<Spinner>(R.id.calc_spinner)
        val adapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.calcSpinner,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter);

        calculateDoseObservable()
        buttonCalc.setOnClickListener {
            calculateDose()
        }
    }

    private fun calculateDoseObservable() {
        viewModel.calculateDoseObserverable().observe(this, Observer <Double?>{
            if(it == null) {
                Toast.makeText(this@CalculateDoseActivity, "Failed to calculate...", Toast.LENGTH_LONG).show()
            } else {
                result.visibility = View.VISIBLE
                result.setText(result.text.toString()+it.toString() + " + ");
            }
        })
    }

    fun calculateDose(){
        val request = CalculateDoseRequest(calc_spinner.selectedItemPosition+1, CountCalc.text.toString().toInt(),
        WeightCalc.text.toString().toInt())
        viewModel.calculateDose(applicationContext, request)
    }
}