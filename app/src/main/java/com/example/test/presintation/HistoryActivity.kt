package com.example.test.presintation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.data_source.IRequestToGecko
import com.example.test.di.CoinModule
import com.example.test.domain.models.Coin
import com.example.test.presintation.adapter.HistoryAdapter
import com.example.test.presintation.viewModel.HistoryModelFacroty
import com.example.test.presintation.viewModel.HistoryViewModel
import com.squareup.picasso.Picasso

class HistoryActivity : AppCompatActivity() {

    private val geckoModule: IRequestToGecko = CoinModule.provideCoinGeckoApi()
    private lateinit var vm: HistoryViewModel
    private lateinit var name: TextView
    private lateinit var marketCap: TextView
    private lateinit var symbol: TextView
    private lateinit var price: TextView
    private lateinit var imageView: ImageView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var recyclerHistoryView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coin_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val coin = intent.getParcelableExtra<Coin>("coin")
        vm = ViewModelProvider(this, HistoryModelFacroty(geckoModule)).get(HistoryViewModel::class.java)

        vm.GetCoinHistory(coin?.id.toString(), coin?.currency.toString())
        recyclerHistoryView = findViewById(R.id.historyView)
        name = findViewById(R.id.coinName)
        marketCap = findViewById(R.id.marketCap)
        symbol = findViewById(R.id.symbol)
        price = findViewById(R.id.price)
        imageView = findViewById(R.id.imageView2)

        recyclerHistoryView.setHasFixedSize(false)
        recyclerHistoryView.layoutManager = LinearLayoutManager(this)

        vm.mutableHistoryData.observe(this, Observer{
            historyAdapter = HistoryAdapter(it, coin?.currency.toString())
            recyclerHistoryView.adapter = historyAdapter
        })

        Picasso.get().load(coin?.image).into(imageView)
        name.text = coin?.name
        marketCap.text = "Market cap: ${coin?.market_cap.toString()}"
        symbol.text = coin?.symbol.toString().uppercase()
        price.text = "${coin?.price.toString()} ${coin?.currency.toString().uppercase()}"
    }
}