package com.example.test.presintation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class HistoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val DateTime = itemView.findViewById<TextView>(R.id.dateTime)
    val Price = itemView.findViewById<TextView>(R.id.price)
}