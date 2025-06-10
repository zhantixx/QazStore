package com.example.qazstore.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    @Query("SELECT * FROM order_items")
    fun getAllOrders(): LiveData<List<OrderEntity>>
}
