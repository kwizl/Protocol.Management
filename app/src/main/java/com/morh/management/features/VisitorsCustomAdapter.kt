package com.morh.management.features

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.morh.management.models.Visitor
import com.morh.protocolmanagement.R

class VisitorsCustomAdapter(private var visitorList : List<Visitor>?) : RecyclerView.Adapter<VisitorsCustomAdapter.MyViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_visitor, parent, false)
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

            holder.textContact.setOnLongClickListener(View.OnLongClickListener {
                val clipboardManager = getSystemService(context, ClipboardManager::class.java) as ClipboardManager
                val clipData = ClipData.newPlainText("Contact", holder.textContact.text)
                clipboardManager.setPrimaryClip(clipData)

                val toast = Toast.makeText(context, "Contact Copied", Toast.LENGTH_LONG)
                toast.show()

                true
            })
        }
    }

    override fun getItemCount(): Int {
        return visitorList?.size ?: 0
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textName : TextView = itemView.findViewById(R.id.Visitor_Name)
        val textContact : TextView = itemView.findViewById(R.id.Visitor_Contact)
    }
}