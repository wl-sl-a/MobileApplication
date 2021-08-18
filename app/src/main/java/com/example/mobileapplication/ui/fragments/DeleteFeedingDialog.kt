package com.example.mobileapplication.ui.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.api.responses.FeedingResponse
import com.example.mobileapplication.viewmodels.FeedingActivityViewModel

class DeleteFeedingDialog(val idFeeding: String) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
                .setMessage("Do you really want to delete the feeding number "+idFeeding+"?")
                .setCancelable(true)
                .setPositiveButton("YES") { dialog, id ->
                    deleteFeeding(idFeeding.toInt(), activity, it.applicationContext)
                }
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Feeding is not deleted",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun deleteFeeding(id: Int, activity: FragmentActivity?, context: Context) {
        lateinit var viewModel: FeedingActivityViewModel
        viewModel = ViewModelProvider(this).get(FeedingActivityViewModel::class.java)
        viewModel.deleteFeedingObserverable().observe(this, Observer <FeedingResponse?>{
            if(it == null) {
                Toast.makeText(activity, "Failed to delete feeding...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Successfully deleted feeding...", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.deleteFeeding(context, id)
        activity?.finish()
    }
}