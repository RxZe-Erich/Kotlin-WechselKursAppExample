package com.example.test.presintation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.models.Coin
import com.example.test.domain.usecases.GetAllCoinsUseCase
import com.example.test.data.CoinRequestParams
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoinViewModel (
    private val getAllCoins: GetAllCoinsUseCase,
) : ViewModel() {

    var currencyLiveData = MutableLiveData<List<Coin>>()
    var timerLiveData = MutableLiveData<Long>()
    var updateIsActive = true
    lateinit var coinParams: CoinRequestParams
    private var dataUpdateJob: Job? = null

    private var isAscendingPrice = false
    private var isAscendingPrecentage = false

    init
    {
        SetParameters()
        GetCoins()
        CoinAutoUpdate()
    }

    fun GetSortByPriceValue(): Boolean {
        return isAscendingPrice
    }

    fun GetSortByPrecentageValue(): Boolean {
        return isAscendingPrecentage
    }

    fun SetParameters(currencyType: String = "eur", precision: Int = 2, page: Int = 1, per_page: Int = 250) {
        coinParams = CoinRequestParams(
            currencyType = currencyType,
            precision = precision,
            page = page,
            per_page = per_page
        )
    }

    fun GetCoins(currencyType: String = "eur", page: Int = 1, per_page: Int = 250, precision: Int = 2)
    {
        viewModelScope.launch {
            val result = getAllCoins.execute(currencyType, page, per_page, precision)
            if(result != null) {
                currencyLiveData.postValue(result)
            }
        }
    }

    private fun CoinAutoUpdate() {
        viewModelScope.launch {
            var timeRemaining: Long = 60000
            val delayMillis: Long = 1000
            while (updateIsActive) {
                while (updateIsActive && timeRemaining > 0) {
                    UpdateTimer(timeRemaining)
                    delay(delayMillis)
                    timeRemaining -= delayMillis
                }
                val result = getAllCoins.execute(coinParams.currencyType, coinParams.page, coinParams.per_page, coinParams.precision)
                if(result != null) {
                    currencyLiveData.postValue(result)
                }
                timeRemaining = 60000
            }
        }
    }

    private fun UpdateTimer(timerRemaining: Long) {
        timerLiveData.postValue(timerRemaining)
    }

    fun SortByPrice()
    {
        if(!isAscendingPrice) {
            currencyLiveData.postValue(currencyLiveData.value?.sortedBy { it.price })
            isAscendingPrice = true
        }
        else {
            currencyLiveData.postValue(currencyLiveData.value?.sortedByDescending { it.price })
            isAscendingPrice = false
        }

    }

    fun SortByPricePercentage()
    {
        if(!isAscendingPrecentage) {
            currencyLiveData.postValue(currencyLiveData.value?.sortedBy { it.price_precentage_change })
            isAscendingPrecentage = true
        }
        else {
            currencyLiveData.postValue(currencyLiveData.value?.sortedByDescending { it.price_precentage_change })
            isAscendingPrecentage = false
        }

    }
}