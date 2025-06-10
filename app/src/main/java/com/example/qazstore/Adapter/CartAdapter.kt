package com.example.qazstore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qazstore.Data.CartManager
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.databinding.ViewholderCartBinding

class CartAdapter(
    private val items: MutableList<Pair<ProductModel, Int>>, // ProductModel to quantity
    private val onUpdate: () -> Unit // callback to update summary
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val (product, quantity) = items[position]

        holder.binding.apply {
            textViewTitle.text = product.title
            textViewPrice.text = "${(product.price.replace("\\D".toRegex(), "").toIntOrNull() ?: 0) * quantity} tg"
            textViewQuantity.text = quantity.toString()
            imageViewProduct.setImageResource(product.imageResId)

            buttonPlus.setOnClickListener {
                CartManager.changeQuantity(product, 1)
                refresh()
            }

            buttonMinus.setOnClickListener {
                CartManager.changeQuantity(product, -1)
                refresh()
            }

        }
    }

    private fun refresh() {
        items.clear()
        items.addAll(CartManager.getCart().toList())
        notifyDataSetChanged()
        onUpdate()
    }

    override fun getItemCount(): Int = items.size
}
