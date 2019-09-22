package com.example.android_fridge.data

class ItemRepository private constructor(private val itemDao: ItemDao) {

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