package com.morh.management.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.models.Member
import com.morh.protocolmanagement.R

class MembersCustomAdapter(private val memberList : List<Member>?) : RecyclerView.Adapter<MembersCustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_member, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = memberList?.get(position)

        if (currentItem != null) {
            holder.textName.text = currentItem.Name
            holder.textContact.text = currentItem.Contact
        }
    }

    override fun getItemCount(): Int {
        return memberList?.size ?: 0
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName : TextView = itemView.findViewById(R.id.Name)
        val textContact : TextView = itemView.findViewById(R.id.Contact)
    }
}