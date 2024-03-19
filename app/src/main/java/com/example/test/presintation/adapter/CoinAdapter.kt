package com.example.test.presintation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.domain.models.Coin
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.Locale

class CoinAdapter(private val currencyDataList: List<Coin>) : RecyclerView.Adapter<CoinViewHolder>() {

    var onItemClick: ((Coin) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_view , parent, false)
        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyDataList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = currencyDataList[position]
        Picasso.get().load(coin.image).into(holder.CoinImage)
        holder.CoinName.text = coin.name
        holder.CurrentPrice.text = "Current price: ${coin.price} ${coin.currency.uppercase()}"
        holder.Symbol.text = coin.symbol.uppercase()

        if (coin.price_precentage_change > 0) {
            holder.PercentageChange.setTextColor(Color.parseColor("#199525"))
        }
        else if(coin.price_precentage_change < 0) {
            holder.PercentageChange.setTextColor(Color.parseColor("#F14C36"))
        }
        holder.PercentageChange.text = "${formatPrecision(coin.price_precentage_change)}%"

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(coin)
        }
    }

    private fun formatPrecision(formatedNummber: Double) : String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        numberFormat.maximumFractionDigits = 2
        return numberFormat.format(formatedNummber)
    }
}