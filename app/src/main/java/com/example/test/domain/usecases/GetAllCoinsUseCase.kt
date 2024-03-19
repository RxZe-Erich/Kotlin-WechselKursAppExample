package com.example.test.domain.usecases

import android.net.http.HttpResponseCache
import android.util.Log
import android.widget.Toast
import com.example.test.GeckoConstant
import com.example.test.domain.models.Coin
import com.example.test.domain.repository.ICoinRepository
import com.google.api.Context
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

class GetAllCoinsUseCase (private val repository: ICoinRepository) {
    private var currency: List<Coin> = listOf()

    suspend fun execute(
        currencyType: String,
        page: Int,
        per_page: Int,
        precision: Int,
        apiKey: String = GeckoConstant.API_KEY
    ) : List<Coin> {
        try{
            currency = repository.GetMarkets(currencyType, precision, page, per_page, apiKey)
        }
        catch (e: Exception) {
            Log.e("TAG", "Error occured ${e.message}")
        }
        return currency
    }
}