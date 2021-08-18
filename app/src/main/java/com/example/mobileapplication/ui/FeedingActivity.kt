package com.example.mobileapplication.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Feeding
import com.example.mobileapplication.ui.adapters.FeedingAdapter
import com.example.mobileapplication.utils.AquariumManager
import com.example.mobileapplication.utils.SessionManager
import com.example.mobileapplication.viewmodels.FeedingActivityViewModel
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_feeding.*
import kotlinx.android.synthetic.main.activity_main.*


class FeedingActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: FeedingAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var dialog: AlertDialog

    lateinit var viewModel: FeedingActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeding)
        sessionManager = SessionManager(this)

        recyclerFeedingList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerFeedingList.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        initViewModel()
        btnAddFeeding.setOnClickListener {
            startActivity(Intent(this@FeedingActivity, CreateNewFeedingActivity::class.java))
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
        viewModel = ViewModelProvider(this).get(FeedingActivityViewModel::class.java)
        viewModel.getFeedingListObserverable().observe(this, Observer<MutableList<Feeding>> {
            if(it == null) {
                Toast.makeText(this@FeedingActivity, "no result found...", Toast.LENGTH_LONG).show()
            } else {
                println("PPPPPPPPPPPPPPPPPPPP"+it[0])
                adapter = FeedingAdapter(baseContext, it)
                adapter.notifyDataSetChanged()
                recyclerFeedingList.adapter = adapter
                dialog.dismiss()
            }
        })
        viewModel.getFeedingsList(applicationContext)
    }
}