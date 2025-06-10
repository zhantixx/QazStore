package com.example.qazstore.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.qazstore.Domain.ProductModel

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(product: ProductModel)

    @Query("SELECT * FROM products")
    fun getAllFavorites(): LiveData<List<ProductModel>>

    @Delete
    suspend fun deleteFavorite(product: ProductModel)
}