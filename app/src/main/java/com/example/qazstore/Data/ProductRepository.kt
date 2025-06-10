package com.example.qazstore.Data

import androidx.lifecycle.LiveData
import com.example.qazstore.Domain.ProductModel

class ProductRepository(
    private val cartDao: CartDao,
    private val favoriteDao: FavoriteDao
) {
    fun getCartItems(): LiveData<List<ProductModel>> = cartDao.getAllCartItems()

    suspend fun addToCart(product: ProductModel) = cartDao.insertCartItem(product)
    suspend fun removeFromCart(product: ProductModel) = cartDao.deleteCartItem(product)
    suspend fun updateCartItem(product: ProductModel) = cartDao.updateCartItem(product)
    suspend fun clearCart() = cartDao.clearCart()

    fun getFavorites(): LiveData<List<ProductModel>> = favoriteDao.getAllFavorites()
    suspend fun addToFavorites(product: ProductModel) = favoriteDao.insertFavorite(product)
    suspend fun removeFromFavorites(product: ProductModel) = favoriteDao.deleteFavorite(product)
}
