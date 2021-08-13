package com.example.mobileapplication.ui.fragments

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplication.api.responses.AquariumResponse
import com.example.mobileapplication.ui.MainActivity
import com.example.mobileapplication.viewmodels.MainActivityViewModel

class DeleteAquariumDialog(val idAqua: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
                .setMessage("Do you really want to delete the aquarium number "+idAqua+"?")
                .setCancelable(true)
                .setPositiveButton("YES") { dialog, id ->
                    deleteAquarium(idAqua.toInt(), activity, it.applicationContext)
                }
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Возможно вы правы",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun deleteAquarium(id: Int, activity: FragmentActivity?, context: Context) {
        lateinit var viewModel: MainActivityViewModel
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.deleteAquariumObserverable().observe(this, Observer <AquariumResponse?>{
            if(it == null) {
                Toast.makeText(activity, "Failed to delete aquarium...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Successfully deleted aquarium...", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.deleteAquarium(context, id)
        activity?.finish()
        var refresh = Intent(activity?.applicationContext, MainActivity::class.java)
        startActivity(refresh)
        refresh = Intent(activity?.applicationContext, MainActivity::class.java)
        startActivity(refresh)
    }
}
