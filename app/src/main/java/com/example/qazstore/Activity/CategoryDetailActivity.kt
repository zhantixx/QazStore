package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qazstore.Adapter.CategoryDetailAdapter
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.ViewModel.MainViewModel
import com.example.qazstore.databinding.ActivityCategoryDetailBinding

class CategoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryDetailBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var productAdapter: CategoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryId = intent.getIntExtra("categoryId", -1)
        if (categoryId == -1) {
            finish()
            return
        }

        viewModel.selectCategory(categoryId)

        productAdapter = CategoryDetailAdapter(mutableListOf()) { product ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.PRODUCT_KEY, product)
            startActivity(intent)

        }

        binding.viewDetCat.layoutManager = LinearLayoutManager(this)
        binding.viewDetCat.adapter = productAdapter

        viewModel.filteredProducts.observe(this) { filtered ->
            productAdapter.updateList(filtered)
        }

        binding.backBtn5.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
