package com.example.test.presintation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.Currencies
import com.example.test.data.data_source.IRequestToGecko
import com.example.test.di.CoinModule
import com.example.test.presintation.adapter.CoinAdapter
import com.example.test.presintation.viewModel.CoinViewModel
import com.example.test.presintation.viewModel.CoinViewModelFactory
import okhttp3.internal.format


class MainActivity : AppCompatActivity() {

    private lateinit var vm: CoinViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var imageButton: ImageView
    private lateinit var spinnerList: Spinner
    private lateinit var currencies: Array<String>
    private lateinit var updateText: TextView
    private lateinit var sortByPriceButton: Button
    private lateinit var sortByPrecentageButton: Button
    private val geckoModule: IRequestToGecko = CoinModule.provideCoinGeckoApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        recyclerView = findViewById<RecyclerView>(R.id.coinsView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setDataToSpinner()
        vm = ViewModelProvider(this, CoinViewModelFactory(geckoModule)).get(CoinViewModel::class.java)

        sortByPriceButton.setOnClickListener {
            vm.SortByPrice()
            if(!vm.GetSortByPriceValue()) {
                sortByPriceButton.text = "Price▲"
            }
            else {
                sortByPriceButton.text = "Price▼"
            }
        }

        sortByPrecentageButton.setOnClickListener {
            vm.SortByPricePercentage()
            if(!vm.GetSortByPrecentageValue()) {
                sortByPrecentageButton.text = "Precentage▲"
            }
            else {
                sortByPrecentageButton.text = "Precentage▼"
            }
        }

        vm.currencyLiveData.observe(this, Observer{
            coinAdapter = CoinAdapter(it)
            recyclerView.adapter = coinAdapter

            initHistoryActivity(coinAdapter)
        })

        vm.timerLiveData.observe(this, Observer{timeRemaining ->
            val seconds = (timeRemaining / 1000) % 60
            updateText.text = "Next update in: ${seconds} seconds"
        })
    }

    fun initViews() {
        spinnerList = findViewById(R.id.walletChange)
        updateText = findViewById(R.id.updateText)
        sortByPriceButton = findViewById(R.id.buttonSortPrice)
        sortByPrecentageButton = findViewById(R.id.buttonSortPrecentage)

    }

    fun initHistoryActivity(coinAdapter: CoinAdapter) {
        coinAdapter.onItemClick = {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putExtra("coin", it)
            startActivity(intent)
        }
    }

    fun setDataToSpinner() {
        currencies = Currencies().GetListOfCurrencies()

        spinnerList.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line,
            currencies)

        spinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                vm.SetParameters(currencies[position].lowercase())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                for (currency in currencies) {
                    if (currency == "EUR") {
                        vm.GetCoins(currency.lowercase())
                    }
                }
            }

        }
    }
}