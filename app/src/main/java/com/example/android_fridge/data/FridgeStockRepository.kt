package com.example.android_fridge.data

class FridgeStockRepository private constructor(
    private val fridgeStockDao: FridgeStockDao
) {

    suspend fun createFridgeStock(itemId: String) {
        val fridgeStock = FridgeStock(itemId)
        fridgeStockDao.insertFridgeStock(fridgeStock)
    }

    suspend fun removeFridgeStock(fridgeStock: FridgeStock) {
        fridgeStockDao.deleteFridgeStock(fridgeStock)
    }

    fun isStocked(itemId: String) =
        fridgeStockDao.isStocked(itemId)

    fun getStockedItems() =
        fridgeStockDao.getStockedItems()

    companion object {
        @Volatile private var instance: FridgeStockRepository? = null

        fun getInstance(fridgeStockDao: FridgeStockDao) =
            instance ?: synchronized(this) {
                instance ?: FridgeStockRepository(fridgeStockDao).also { instance = it }
            }
    }
}