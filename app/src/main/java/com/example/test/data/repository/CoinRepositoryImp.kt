package com.example.test.data.repository

import com.example.test.data.data_source.IRequestToGecko
import com.example.test.domain.models.Coin
import com.example.test.domain.repository.ICoinRepository

class CoinRepositoryImp (coinGeckoApi: IRequestToGecko) : ICoinRepository {
    val _coinGeckoApi: IRequestToGecko = coinGeckoApi
    override suspend fun GetMarkets(
        currencyType: String,
        precision: Int,
        page: Int,
        per_page: Int,
        apiKey: String
    ): List<Coin> {
        return _coinGeckoApi.getCoinInfo(currencyType, precision, page, per_page, apiKey).map {
            it.toCoin(currencyType)
        }
    }
}