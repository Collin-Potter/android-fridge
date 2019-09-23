package com.example.android_fridge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android_fridge.data.FridgeStockRepository
import com.example.android_fridge.data.ItemAndFridgeStock

class FridgeStockListViewModel internal constructor(
    fridgeStockRepository: FridgeStockRepository
) : ViewModel() {
    val itemAndFridgeStock: LiveData<List<ItemAndFridgeStock>> =
        fridgeStockRepository.getStockedItems()
}