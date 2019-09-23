package com.example.android_fridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_fridge.data.FridgeStockRepository

class FridgeStockListViewModelFactory(
    private val repository: FridgeStockRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FridgeStockListViewModel(repository) as T
    }
}