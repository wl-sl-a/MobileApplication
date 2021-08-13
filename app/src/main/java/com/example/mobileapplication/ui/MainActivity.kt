package com.example.mobileapplication.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplication.R
import com.example.mobileapplication.api.ApiClient
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.ui.adapters.AquariumAdapter
import com.example.mobileapplication.utils.AquariumManager
import com.example.mobileapplication.utils.SessionManager
import com.example.mobileapplication.viewmodels.MainActivityViewModel
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.aquarium_layout.*
import kotlinx.android.synthetic.main.aquarium_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: AquariumAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: AlertDialog

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sessionManager = SessionManager(this)

        recyclerAquariumList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerAquariumList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        initViewModel()

        btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateNewAquariumActivity::class.java))
            Toast.makeText(this@MainActivity, AquariumManager(this).fetchId(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_fav -> {
            LogOut()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun LogOut(){
        sessionManager = SessionManager(this)
        sessionManager.terminateSession()

        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent);
    }

    fun initViewModel(){
        dialog.show()
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAquariumListObserverable().observe(this, Observer<MutableList<Aquarium>> {
            if(it == null) {
                Toast.makeText(this@MainActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                adapter = AquariumAdapter(baseContext, it)
                adapter.notifyDataSetChanged()
                recyclerAquariumList.adapter = adapter
                dialog.dismiss()
            }
        })
        viewModel.getAquariumsList(applicationContext)
    }

    fun deleteAquarium(id: Int) {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.deleteAquariumObserverable().observe(this, Observer <AquariumResponse?>{
            if(it == null) {
                Toast.makeText(this@MainActivity, "Failed to delete aquarium...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "Successfully deleted aquarium...", Toast.LENGTH_LONG).show()
                finish()
            }
        })
        viewModel.deleteAquarium(applicationContext, id)
    }
}

