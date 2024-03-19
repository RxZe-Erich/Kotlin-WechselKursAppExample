package com.example.test.domain.usecases

import android.util.Log
import com.example.test.GeckoConstant
import com.example.test.data.data_source.DTO.MarketChart
import com.example.test.data.repository.HistoryRepositoryImp
import com.example.test.domain.models.PriceHistory
import com.example.test.domain.repository.IHistoryRepository

class GetCoinHistoryById(private val repository: IHistoryRepository) {
    var historyList: List<PriceHistory> = listOf()
    suspend fun execute(
        id: String,
        currencyType: String,
        days: Int,
        interval: String = "daily",
        precision: Int = 2,
        apiKey: String = GeckoConstant.API_KEY
    ) : List<PriceHistory> {
        try {
            return repository.GetHistoryById(id, currencyType, days, interval, precision, apiKey)
        }
        catch (e: Exception) {
            Log.e("TAG", "Error occured ${e.message}")
            return historyList
        }
    }
}