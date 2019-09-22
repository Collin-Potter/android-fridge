package com.example.android_fridge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_fridge.data.ItemRepository

class ItemListViewModelFactory(
    private val repository: ItemRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = ItemListViewModel(repository) as T
}