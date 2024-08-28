package com.morh.management.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.models.Member
import com.morh.protocolmanagement.R

class CustomAdapter(private val memberList : ArrayList<Member>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_member, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = memberList[position]
        holder.textName.text = currentItem.Name
        holder.textPhoneNumber.text = currentItem.Name
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName : TextView = itemView.findViewById(R.id.Name)
        val textPhoneNumber : TextView = itemView.findViewById(R.id.PhoneNumber)
    }
}