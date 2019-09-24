package com.example.android_fridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_fridge.data.FridgeStockRepository
import com.example.android_fridge.data.ItemRepository
import kotlinx.coroutines.launch

class ItemDetailViewModel(
    itemRepository: ItemRepository,
    private val fridgeStockRepository: FridgeStockRepository,
    private val itemId: String
) : ViewModel() {

    val isStocked = fridgeStockRepository.isStocked(itemId)
    val item = itemRepository.getItem(itemId)

    fun addItemToFridge() {
        viewModelScope.launch {
            fridgeStockRepository.createFridgeStock(itemId)
        }
    }

    fun removeItemFromFridge() {
        viewModelScope.launch {
            val tempStock = fridgeStockRepository.getStockedItemById(itemId)
            fridgeStockRepository.removeFridgeStock(tempStock)
        }
    }
}