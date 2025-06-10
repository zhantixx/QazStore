package com.example.qazstore.Data

import com.example.qazstore.Domain.ProductModel

object CartManager {
    private val cartItems = mutableMapOf<ProductModel, Int>()

    fun addToCart(product: ProductModel) {
        cartItems[product] = (cartItems[product] ?: 0) + 1
    }

    fun removeFromCart(product: ProductModel) {
        cartItems.remove(product)
    }

    fun changeQuantity(product: ProductModel, delta: Int) {
        val current = cartItems[product] ?: return
        val newQuantity = current + delta
        if (newQuantity <= 0) {
            cartItems.remove(product)
        } else {
            cartItems[product] = newQuantity
        }
    }

    fun updateQuantity(product: ProductModel, quantity: Int) {
        if (quantity <= 0) {
            cartItems.remove(product)
        } else {
            cartItems[product] = quantity
        }
    }

    fun getCart(): Map<ProductModel, Int> = cartItems.toMap()

    fun getTotalPrice(): Int = cartItems.entries.sumOf { (it.key.price.toIntOrNull() ?: 0) * it.value }

    fun getTotalQuantity(): Int = cartItems.values.sum()

    fun clear() = cartItems.clear()
}
