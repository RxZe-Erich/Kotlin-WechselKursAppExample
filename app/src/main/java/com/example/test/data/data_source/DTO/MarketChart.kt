package com.example.test.data.data_source.DTO

data class MarketChart(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
){
}