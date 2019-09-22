package com.example.android_fridge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android_fridge.data.Item
import com.example.android_fridge.data.ItemRepository

class ItemListViewModel internal constructor(itemRepository: ItemRepository) : ViewModel() {

    val items: LiveData<List<Item>> = itemRepository.getItems()

}