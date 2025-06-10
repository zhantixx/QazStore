package com.example.qazstore.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: String,
    val imageResId: Int
)
