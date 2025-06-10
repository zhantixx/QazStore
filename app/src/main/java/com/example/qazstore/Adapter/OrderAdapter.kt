package com.example.qazstore.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qazstore.Data.OrderEntity
import com.example.qazstore.databinding.ViewholderHistoryBinding

class OrderAdapter(
    private val onReturnClick: (OrderEntity) -> Unit
) : ListAdapter<OrderEntity, OrderAdapter.OrderViewHolder>(DiffCallback()) {

    inner class OrderViewHolder(val binding: ViewholderHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = ViewholderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        holder.binding.productTitle.text = order.title
        holder.binding.productPrice.text = order.price
        Glide.with(holder.binding.productImage.context)
            .load(order.imageResId)
            .into(holder.binding.productImage)


    }

    class DiffCallback : DiffUtil.ItemCallback<OrderEntity>() {
        override fun areItemsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OrderEntity, newItem: OrderEntity): Boolean {
            return oldItem == newItem
        }
    }
}
