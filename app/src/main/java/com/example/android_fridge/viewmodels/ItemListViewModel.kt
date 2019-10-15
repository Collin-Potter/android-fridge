package com.example.android_fridge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android_fridge.data.Item
import com.example.android_fridge.data.ItemRepository
import com.example.android_fridge.data.ItemResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class ItemListViewModel internal constructor(itemRepository: ItemRepository) : ViewModel() {

    val items: LiveData<List<Item>> = itemRepository.getItems()

//    val response: Response<ItemResponse> = itemRepository.getFoodOptions()

}