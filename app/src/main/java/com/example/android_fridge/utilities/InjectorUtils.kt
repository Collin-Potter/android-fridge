package com.example.android_fridge.utilities

import android.content.Context
import com.example.android_fridge.data.AppDatabase
import com.example.android_fridge.data.ItemRepository
import com.example.android_fridge.viewmodels.ItemListViewModelFactory

object InjectorUtils {

    private fun getItemRepository(context: Context): ItemRepository {
        return ItemRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).itemDao())
    }

    fun provideItemListViewModelFactory(context: Context): ItemListViewModelFactory {
        val repository = getItemRepository(context)
        return ItemListViewModelFactory(repository)
    }
}