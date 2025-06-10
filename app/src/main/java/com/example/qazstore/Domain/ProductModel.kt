package com.example.qazstore.Domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: String,
    val imageResId: Int,
    val brand: String,
    val description: String,
    var quantity: Int = 1,
    val categoryId: Int
): Serializable