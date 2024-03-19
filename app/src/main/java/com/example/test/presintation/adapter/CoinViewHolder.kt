package com.example.test.presintation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val CoinImage = itemView.findViewById<ImageView>(R.id.coinImage)
    val CoinName = itemView.findViewById<TextView>(R.id.coinName)
    val Symbol = itemView.findViewById<TextView>(R.id.symbol)
    val CurrentPrice = itemView.findViewById<TextView>(R.id.currentPrice)
    val PercentageChange = itemView.findViewById<TextView>(R.id.percentageChange24h)
}