package com.example.test.domain.repository

import com.example.test.data.data_source.DTO.MarketChart
import com.example.test.domain.models.PriceHistory

interface IHistoryRepository {
    suspend fun GetHistoryById(id: String, currencyType: String, days: Int, interval: String, precision: Int, apiKey: String) : List<PriceHistory>
}