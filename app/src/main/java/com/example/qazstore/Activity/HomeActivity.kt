package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle

import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qazstore.Adapter.CategoryAdapter
import com.example.qazstore.Adapter.ProductAdapter
import com.example.qazstore.Adapter.SliderAdapter
import com.example.qazstore.Domain.CategoryModel
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.R
import com.example.qazstore.ViewModel.MainViewModel
import com.example.qazstore.databinding.ActivityHomeBinding
import java.util.*
import android.os.Handler
import android.os.Looper


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: MainViewModel by viewModels()
    private var fullProductList: List<ProductModel> = emptyList()
    private lateinit var productAdapter: ProductAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var sliderRunnable: Runnable? = null
    private var currentIndex = 0
    private val imageList = listOf(
        R.drawable.slider1,
        R.drawable.slider2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopProducts()
        initSearch()
        setupAutoSlide()


        binding.notImg.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }


        val sliderAdapter = SliderAdapter(imageList)
        binding.sliderViewPager.adapter = sliderAdapter

        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> true
                R.id.navigation_like -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    true
                }
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

    private fun initCategory() {
        binding.progressBarCat.visibility = View.VISIBLE
        viewModel.categories.observe(this) { categoryList ->
            binding.progressBarCat.visibility = View.GONE
            val adapter = CategoryAdapter(categoryList) { category ->
                val intent = Intent(this, CategoryDetailActivity::class.java)
                intent.putExtra("categoryId", category.id)
                startActivity(intent)
            }
            binding.catView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.catView.adapter = adapter
        }
    }

    private fun initTopProducts() {
        binding.progressBarTop.visibility = View.VISIBLE
        viewModel.products.observe(this) { products ->
            binding.progressBarTop.visibility = View.GONE
            fullProductList = products
            productAdapter = ProductAdapter(products.toMutableList()) { selectedProduct ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("product", selectedProduct)
                startActivity(intent)
            }
            binding.topView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.topView.adapter = productAdapter
        }
    }

    private fun initSearch() {
        binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                val filteredList = fullProductList.filter {
                    it.title.contains(query, ignoreCase = true)
                }
                productAdapter.updateList(filteredList)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    private fun setupAutoSlide() {
        sliderRunnable = Runnable {
            if (currentIndex == imageList.size) currentIndex = 0
            binding.sliderViewPager.setCurrentItem(currentIndex++, true)
            handler.postDelayed(sliderRunnable!!, 3000) // каждые 3 секунды
        }
        handler.postDelayed(sliderRunnable!!, 3000)
    }

}