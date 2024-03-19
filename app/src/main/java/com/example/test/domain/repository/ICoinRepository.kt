package com.example.test.domain.repository

import com.example.test.domain.models.Coin

interface ICoinRepository {
    suspend fun GetMarkets(currencyType: String, precision: Int, page: Int, per_page: Int, apiKey: String) : List<Coin>
}