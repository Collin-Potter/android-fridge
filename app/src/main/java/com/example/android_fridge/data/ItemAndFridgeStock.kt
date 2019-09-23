package com.example.android_fridge.data

import androidx.room.Embedded
import androidx.room.Relation

data class ItemAndFridgeStock(
    @Embedded
    val item: Item,

    @Relation(parentColumn = "id", entityColumn = "item_id")
    val fridgeStock: List<FridgeStock> = emptyList()
)