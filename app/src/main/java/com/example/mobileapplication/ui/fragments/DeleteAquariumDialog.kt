package com.example.mobileapplication.ui.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteAquariumDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
                .setMessage("Do you really want to delete the aquarium?")
                .setCancelable(true)
                .setPositiveButton("YES") { dialog, id ->
                    Toast.makeText(activity, "Вы сделали правильный выбор",
                        Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("NO",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(activity, "Возможно вы правы",
                            Toast.LENGTH_LONG).show()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
