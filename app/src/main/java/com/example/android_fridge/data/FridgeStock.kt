package com.example.android_fridge.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "fridge_stock",
    foreignKeys = [ForeignKey(entity = Item::class, parentColumns = ["id"], childColumns = ["item_id"])],
    indices = [Index("item_id")]
)
class FridgeStock(
    @ColumnInfo(name = "item_id") val itemId: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var fridgeStockingId: Long = 0
}