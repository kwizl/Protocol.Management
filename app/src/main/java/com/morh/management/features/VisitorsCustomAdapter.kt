package com.morh.management.features

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.models.Member
import com.morh.management.models.Visitor
import com.morh.protocolmanagement.R

class VisitorsCustomAdapter(private var visitorList : List<Visitor>?) : RecyclerView.Adapter<VisitorsCustomAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_visitor, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun filteredList(filteredList: List<Visitor>)
    {
        visitorList = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = visitorList?.get(position)

        if (currentItem != null) {
            holder.textName.text = currentItem.Name
            holder.textContact.text = currentItem.Contact
        }
    }

    override fun getItemCount(): Int {
        return visitorList?.size ?: 0
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName : TextView = itemView.findViewById(R.id.Name)
        val textContact : TextView = itemView.findViewById(R.id.Contact)
    }
}