package com.example.mobileapplication.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication.R
import com.example.mobileapplication.api.models.Aquarium
import com.example.mobileapplication.ui.EditAquariumActivity
import com.example.mobileapplication.ui.fragments.DeleteAquariumDialog
import com.example.mobileapplication.utils.AquariumManager
import kotlinx.android.synthetic.main.aquarium_layout.view.*


class AquariumAdapter(private val context: Context, var aquariumList: MutableList<Aquarium>):
    RecyclerView.Adapter<AquariumAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txt_id: TextView = itemView.txt_id
        val txt_manufacturer: TextView = itemView.txt_manufacturer
        val txt_volume: TextView = itemView.txt_volume
        val txt_height: TextView = itemView.txt_height
        val txt_width: TextView = itemView.txt_width
        val txt_length: TextView = itemView.txt_length

        fun bind(listItem: Aquarium) {
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
            }
            itemView.btnDel.setOnClickListener {
                Toast.makeText(it.context, "хочет удалить ${itemView.txt_id.text}", Toast.LENGTH_SHORT).show()
                val aquariumManager = AquariumManager(it.context)
                aquariumManager.saveId(itemView.txt_id.text.toString())
                val deleteAquariumDialog: DeleteAquariumDialog = DeleteAquariumDialog(itemView.txt_id.text.toString())
                val ft: FragmentTransaction = (it.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                deleteAquariumDialog.show(ft, "deleteDialog")
            }
            itemView.btnEdit.setOnClickListener{
                var intent = Intent(it.context, EditAquariumActivity::class.java)
                intent.putExtra("id", itemView.txt_id.text.toString())
                intent.putExtra("manufacturer", itemView.txt_manufacturer.text.toString())
                intent.putExtra("volume", itemView.txt_volume.text.toString())
                intent.putExtra("height", itemView.txt_height.text.toString())
                intent.putExtra("width", itemView.txt_width.text.toString())
                intent.putExtra("length", itemView.txt_length.text.toString())
                it.context.startActivity(intent)
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
        holder.txt_height.text = aquariumList[position].height.toString()
        holder.txt_width.text = aquariumList[position].width.toString()
        holder.txt_length.text = aquariumList[position].length.toString()
    }

}