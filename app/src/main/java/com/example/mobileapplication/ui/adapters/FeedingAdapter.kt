package com.example.mobileapplication.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Feeding
import kotlinx.android.synthetic.main.feeding_layout.view.*

class FeedingAdapter(private val context: Context, var feedingList: MutableList<Feeding>):
    RecyclerView.Adapter<FeedingAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_id_f: TextView = itemView.txt_id_f
        val txt_kind: TextView = itemView.txt_kind
        val txt_dose: TextView = itemView.txt_dose
        val txt_aqua_id: TextView = itemView.txt_aqua_id
        val txt_date_time: TextView = itemView.txt_date_time


        fun bind(listItem: Feeding) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.txt_id_f.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feeding_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = feedingList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = feedingList[position]
        holder.bind(listItem)

        holder.txt_id_f.text = feedingList[position].id.toString()
        holder.txt_kind.text = feedingList[position].kindOfFeed
        holder.txt_dose.text = feedingList[position].dose.toString()
        holder.txt_aqua_id.text = feedingList[position].aquariumId.toString()
        holder.txt_date_time.text = feedingList[position].dateTime.toString()
    }
}