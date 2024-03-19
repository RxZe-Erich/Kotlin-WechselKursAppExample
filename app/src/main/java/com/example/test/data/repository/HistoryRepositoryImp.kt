package com.example.test.data.repository

import com.example.test.data.data_source.DTO.MarketChart
import com.example.test.data.data_source.IRequestToGecko
import com.example.test.domain.models.PriceHistory
import com.example.test.domain.repository.IHistoryRepository

class HistoryRepositoryImp(private val coinGeckoApi: IRequestToGecko) : IHistoryRepository {
    override suspend fun GetHistoryById(
        id: String,
        currencyType: String,
        days: Int,
        interval: String,
        precision: Int,
        apiKey: String
    ): List<PriceHistory> {
        val marketChart = coinGeckoApi.getHistoricalData(id, currencyType, days, interval, precision, apiKey)
        val priceHistoryList: List<PriceHistory> = marketChart.prices.map { priceData ->
            PriceHistory(
                timeStamp = priceData[0].toLong(),
                price = priceData[1]
            )
        }
        return priceHistoryList.sortedByDescending { it.timeStamp }
    }

}