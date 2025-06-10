package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qazstore.Adapter.FavoriteAdapter
import com.example.qazstore.Data.AppDatabase
import com.example.qazstore.R
import com.example.qazstore.databinding.ActivityFavoriteBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        setupBottomNavigation()
    }

    private fun setupRecycler() {
        adapter = FavoriteAdapter()
        binding.likeRecycler.layoutManager = LinearLayoutManager(this)
        binding.likeRecycler.adapter = adapter

        AppDatabase.getDatabase(this).favoriteDao().getAllFavorites().observe(this) { favorites ->
            adapter.submitList(favorites)
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_like -> true
                R.id.navigation_cart -> {
                    startActivity(Intent(this, CartActivity::class.java))
                    true
                }
                R.id.navigation_story -> {
                    startActivity(Intent(this, HistoryActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
