package com.example.qazstore.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qazstore.Activity.DetailActivity
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.databinding.ViewholderCateogryDetailBinding

class CategoryDetailAdapter(
    private val productList: MutableList<ProductModel>,
    private val onItemClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<CategoryDetailAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewholderCateogryDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) {
            binding.favTitle.text = product.title
            binding.favPrice.text = product.price
            binding.favRating.text = "Brand: ${product.brand}"

            Glide.with(binding.root.context)
                .load(product.imageResId)
                .into(binding.favImage)

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCateogryDetailBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun updateList(newList: List<ProductModel>) {
        productList.clear()
        productList.addAll(newList)
        notifyDataSetChanged()
    }
}
