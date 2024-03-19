package com.example.test.presintation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.data.data_source.IRequestToGecko
import com.example.test.data.repository.CoinRepositoryImp
import com.example.test.data.repository.HistoryRepositoryImp
import com.example.test.di.CoinModule
import com.example.test.domain.usecases.GetAllCoinsUseCase
import com.example.test.domain.usecases.GetCoinHistoryById

class CoinViewModelFactory(geckoModule: IRequestToGecko) : ViewModelProvider.Factory {

    val coinRepository: CoinRepositoryImp = CoinRepositoryImp(geckoModule)
    val getAllCoins = GetAllCoinsUseCase(coinRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CoinViewModel(
            getAllCoins
        ) as T
    }
}