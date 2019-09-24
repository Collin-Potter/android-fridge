package com.example.android_fridge.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FridgeStockDao {
    @Query("SELECT * FROM fridge_stock")
    fun getFridgeStock(): LiveData<List<FridgeStock>>

    @Query("SELECT EXISTS(SELECT 1 FROM fridge_stock WHERE item_id = :itemId LIMIT 1)")
    fun isStocked(itemId: String): LiveData<Boolean>

    @Query("SELECT * FROM fridge_stock WHERE item_id = :itemId LIMIT 1")
    suspend fun getFridgeStockByItemId(itemId: String): FridgeStock

    @Transaction
    @Query("SELECT * FROM items WHERE id IN (SELECT DISTINCT(item_id) FROM fridge_stock)")
    fun getStockedItems(): LiveData<List<ItemAndFridgeStock>>

    @Insert
    suspend fun insertFridgeStock(fridgeStock: FridgeStock): Long

    @Delete
    suspend fun deleteFridgeStock(fridgeStock: FridgeStock)
}