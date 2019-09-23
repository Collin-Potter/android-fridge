package com.example.android_fridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_fridge.data.FridgeStockRepository
import com.example.android_fridge.data.ItemRepository

class ItemDetailViewModelFactory(
    private val itemRepository: ItemRepository,
    private val fridgeStockRepository: FridgeStockRepository,
    private val itemId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemDetailViewModel(itemRepository, fridgeStockRepository, itemId) as T
    }
}