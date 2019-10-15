package com.example.android_fridge.data

import com.example.android_fridge.services.ApiFactory

class ItemRepository private constructor(private val itemDao: ItemDao) {

//    fun getFoodOptions() = ApiFactory.foodsApi.getFoodOptions()

    fun getItems() = itemDao.getItems()

    fun getItem(itemId: String) = itemDao.getItem(itemId)

    companion object {

        @Volatile private var instance: ItemRepository? = null

        fun getInstance(itemDao: ItemDao) =
            instance ?: synchronized(this) {
                instance ?: ItemRepository(itemDao).also { instance = it}
            }
    }
}