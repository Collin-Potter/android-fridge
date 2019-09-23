package com.example.android_fridge.utilities

import android.content.Context
import com.example.android_fridge.data.AppDatabase
import com.example.android_fridge.data.FridgeStockRepository
import com.example.android_fridge.data.ItemRepository
import com.example.android_fridge.viewmodels.FridgeStockListViewModelFactory
import com.example.android_fridge.viewmodels.ItemDetailViewModelFactory
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

    fun provideItemDetailViewModelFactory(
        context: Context,
        itemId: String
    ): ItemDetailViewModelFactory {
        return ItemDetailViewModelFactory(getItemRepository(context),
            getFridgeStockRepository(context), itemId)
    }

    fun provideFridgeStockListViewModelFactory(
        context: Context
    ): FridgeStockListViewModelFactory {
        val repository = getFridgeStockRepository(context)
        return FridgeStockListViewModelFactory(repository)
    }

    private fun getFridgeStockRepository(context: Context): FridgeStockRepository {
        return FridgeStockRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).fridgeStockDao())
    }
}