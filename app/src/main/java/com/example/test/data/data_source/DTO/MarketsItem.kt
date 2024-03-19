package com.example.test.data.data_source.DTO

import com.example.test.domain.models.Coin

data class MarketsItem(
    val ath: Double,
    val ath_change_percentage: Double,
    val ath_date: String,
    val atl: Double,
    val atl_change_percentage: Double,
    val atl_date: String,
    val circulating_supply: Double,
    val current_price: Double,
    val fully_diluted_valuation: Long,
    val high_24h: Double,
    val id: String,
    val image: String,
    val last_updated: String,
    val low_24h: Double,
    val market_cap: Long,
    val market_cap_change_24h: Double,
    val market_cap_change_percentage_24h: Double,
    val market_cap_rank: Double,
    val max_supply: Double,
    val name: String,
    val price_change_24h: Double,
    val price_change_percentage_24h: Double,
    val roi: Any,
    val symbol: String,
    val total_supply: Double,
    val total_volume: Double
){
    fun toCoin(currencyType: String) : Coin {
        return Coin(
            id=id,
            name=name,
            image=image,
            symbol = symbol,
            market_cap=market_cap,
            price=current_price,
            price_change_24h = price_change_24h,
            price_precentage_change = price_change_percentage_24h,
            low_price = low_24h,
            high_price = high_24h,
            currency = currencyType
        )
    }
}