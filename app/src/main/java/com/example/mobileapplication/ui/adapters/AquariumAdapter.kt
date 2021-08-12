package com.example.mobileapplication.ui.adapters

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.ui.fragments.DeleteAquariumDialog
import kotlinx.android.synthetic.main.aquarium_layout.view.*


class AquariumAdapter(private val context: Context, var aquariumList: MutableList<Aquarium>):
    RecyclerView.Adapter<AquariumAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_id: TextView = itemView.txt_id
        val txt_manufacturer: TextView = itemView.txt_manufacrurer
        val txt_volume: TextView = itemView.txt_volume


        fun bind(listItem: Aquarium) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
            }
            itemView.btnDel.setOnClickListener {
                Toast.makeText(it.context, "хочет удалить ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.aquarium_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = aquariumList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = aquariumList[position]
        holder.bind(listItem)

        holder.txt_id.text = aquariumList[position].id.toString()
        holder.txt_manufacturer.text = aquariumList[position].manufacturer
        holder.txt_volume.text = aquariumList[position].volume.toString()
    }

}