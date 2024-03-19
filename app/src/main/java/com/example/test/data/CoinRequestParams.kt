package com.example.test.data

data class CoinRequestParams(
    var currencyType: String,
    var precision: Int,
    var page: Int,
    var per_page: Int,
)
{
}