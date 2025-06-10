package com.example.qazstore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.qazstore.Domain.CategoryModel
import com.example.qazstore.R
import com.example.qazstore.databinding.ViewholderCategoryBinding

class CategoryAdapter(
    private val items: List<CategoryModel>,
    private val onItemClick: (CategoryModel) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = items[position]
        holder.binding.titleTxt.text = category.title
        holder.binding.img.setImageResource(category.imageResId)

        holder.binding.root.setOnClickListener {
            onItemClick(category)
        }
    }

    override fun getItemCount(): Int = items.size
}
