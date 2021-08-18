package com.example.mobileapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.requests.FeedingRequest
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.viewmodels.EditFeedingViewModel
import kotlinx.android.synthetic.main.activity_create_new_feeding.*
import kotlinx.android.synthetic.main.activity_edit_aquarium.*
import kotlinx.android.synthetic.main.activity_edit_feeding.*
import java.util.*


class EditFeedingActivity : AppCompatActivity() {
    lateinit var viewModel: EditFeedingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_feeding)
        viewModel = ViewModelProvider(this).get(EditFeedingViewModel::class.java)
        var id = getIntent().getExtras()?.getString("id");
        var dose = getIntent().getExtras()?.getString("dose");
        var kind = getIntent().getExtras()?.getString("kind");
        var aquaId = getIntent().getExtras()?.getString("aquaId");

        val editIdFeeding = findViewById<View>(R.id.editIdFeeding) as EditText
        editIdFeeding.setText(id)
        val editKind = findViewById<View>(R.id.editKind) as EditText
        editKind.setText(kind)
        val editDose = findViewById<View>(R.id.editDose) as EditText
        editDose.setText(dose)
        if (aquaId != null) {
            getAquaIdList(aquaId)
        }
        editFeedingObservable()
        editFeedingButton.setOnClickListener {
            editFeeding()
        }
    }

    private fun editFeedingObservable() {
        viewModel.editFeedingObservable().observe(this, Observer <FeedingResponse?>{
            if(it == null) {
                Toast.makeText(this@EditFeedingActivity, "Failed to update new feeding...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@EditFeedingActivity, "Successfully updated feeding...", Toast.LENGTH_LONG).show()
                val refresh = Intent(this, FeedingActivity::class.java)
                startActivity(refresh)
                finish()
            }
        })
    }

    fun editFeeding(){
        val request = FeedingRequest(editKind.text.toString(), editDose.text.toString().toInt(),
            editAquaId.selectedItem.toString().toInt(), Calendar.getInstance().time.toString(),
            Calendar.getInstance().time.toString()
        )
        viewModel.editFeeding(applicationContext, editIdFeeding.text.toString().toInt(), request)
    }

    private fun selectValue(spinner: Spinner, value: String) {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == value) {
                spinner.setSelection(i)
                break
            }
        }
    }

    fun getAquaIdList(aquaId: String) {
        val spinner = findViewById<Spinner>(R.id.editAquaId)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        viewModel = ViewModelProvider(this).get(EditFeedingViewModel::class.java)
        viewModel.getAquariumListObserverable().observe(this, Observer<MutableList<Aquarium>> {
            if(it == null) {
                Toast.makeText(this@EditFeedingActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                for(item in it){
                    adapter.add(item.id.toString())
                }
                spinner.adapter = adapter
                selectValue(spinner,aquaId)
                spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (parent != null) {
                            feeding_spiner.text = "Selected Aquarium: "+parent.getItemAtPosition(position).toString()
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(this@EditFeedingActivity, "no result found...", Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
        viewModel.getAquariumsList(applicationContext)
    }
}