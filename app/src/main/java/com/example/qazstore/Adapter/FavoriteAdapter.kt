package com.example.qazstore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qazstore.Data.AppDatabase
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private val items = mutableListOf<ProductModel>()

    fun submitList(list: List<ProductModel>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.favImage)
        val title: TextView = itemView.findViewById(R.id.favTitle)
        val price: TextView = itemView.findViewById(R.id.favPrice)
        val brand: TextView = itemView.findViewById(R.id.favRating)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteFav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val product = items[position]

        holder.title.text = product.title
        holder.price.text = "${product.price} tg"
        holder.brand.text = "Brand: ${product.brand}"

        Glide.with(holder.itemView.context)
            .load(product.imageResId)
            .into(holder.image)

        holder.deleteBtn.setOnClickListener {
            val context = holder.itemView.context
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(context).favoriteDao().deleteFavorite(product)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
