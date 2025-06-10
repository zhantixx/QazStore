package com.example.qazstore.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.qazstore.Domain.ProductModel

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(product: ProductModel)

    @Query("SELECT * FROM products")
    fun getAllCartItems(): LiveData<List<ProductModel>>

    @Update
    suspend fun updateCartItem(product: ProductModel)

    @Delete
    suspend fun deleteCartItem(product: ProductModel)

    @Query("DELETE FROM products")
    suspend fun clearCart()
}
