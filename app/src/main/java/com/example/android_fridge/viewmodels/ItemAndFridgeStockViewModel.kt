package com.example.android_fridge.viewmodels

import com.example.android_fridge.data.ItemAndFridgeStock

class ItemAndFridgeStockViewModel(stock: ItemAndFridgeStock) {
    private val item = checkNotNull(stock.item)
    private val fridgeStock = stock.fridgeStock[0]

    val imageUrl
        get() = item.imageUrl
    val itemName
        get() = item.name
    val itemId
        get() = item.itemId
}