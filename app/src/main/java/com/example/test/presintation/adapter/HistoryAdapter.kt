package com.example.test.presintation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.domain.models.PriceHistory
import java.text.SimpleDateFormat
import java.util.Date

class HistoryAdapter(private val priceDataList: List<PriceHistory>, private val currencyType: String) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_view , parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return priceDataList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = priceDataList[position]
        val dateTime = Date(currentItem.timeStamp)
        val dateFormater = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = dateFormater.format(dateTime)
        if(position == 0) {
            holder.DateTime.text = "Today"
            holder.Price.text = "Price: ${currentItem.price} ${currencyType.uppercase()}"
        }
        else {
            holder.DateTime.text = formattedDate
            holder.Price.text = "Price: ${currentItem.price} ${currencyType.uppercase()}"
        }
    }
}