package com.example.qazstore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.R
import com.example.qazstore.databinding.ViewholderItemsBinding

class ProductAdapter(
    private val items: MutableList<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ViewholderItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ViewholderItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = items[position]
        holder.binding.apply {
            titleTxt.text = product.title
            priceTxt.text = product.price
            Glide.with(pic.context).load(product.imageResId).into(pic)
            root.setOnClickListener { onItemClick(product) }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newList: List<ProductModel>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}
