package com.example.test.presintation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.data.data_source.IRequestToGecko
import com.example.test.data.repository.HistoryRepositoryImp
import com.example.test.domain.usecases.GetCoinHistoryById

class HistoryModelFacroty(geckoModule: IRequestToGecko) : ViewModelProvider.Factory {

    private val historyRepository: HistoryRepositoryImp = HistoryRepositoryImp(geckoModule)
    private val getCoinHistoryById = GetCoinHistoryById(historyRepository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(
            getCoinHistoryById
        ) as T
    }
}