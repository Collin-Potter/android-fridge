package com.example.android_fridge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey @ColumnInfo(name = "id") val itemId: String,
    val name: String,
    val description: String,
    val imageUrl: String = ""
)

data class ItemResponse(
    val results: List<Item>
)