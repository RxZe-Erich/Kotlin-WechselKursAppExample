package com.example.test.presintation.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.models.PriceHistory
import com.example.test.domain.usecases.GetCoinHistoryById
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getCoinHistoryById: GetCoinHistoryById
) : ViewModel() {

    var mutableHistoryData = MutableLiveData<List<PriceHistory>>()
    private var requestCounter = 0

    fun GetCoinHistory(coin: String, currencyType: String, days: Int = 14) {
        viewModelScope.launch {
            val result = getCoinHistoryById.execute(coin, currencyType, days)
            if(result != null){
                mutableHistoryData.postValue(result)
            }
        }
    }
}