package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import com.example.qazstore.R
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qazstore.Adapter.CartAdapter
import com.example.qazstore.Data.CartManager
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.databinding.ActivityCartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecycler()
        updateSummary()

        binding.buttonApplyCoupon.setOnClickListener {
            Toast.makeText(this, "Promocode qabyldandy", Toast.LENGTH_SHORT).show()
        }

        binding.buttonPlaceOrder.setOnClickListener {
            showOrderForm()
        }
        findViewById<BottomNavigationView>(R.id.bottomBar).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.navigation_like -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    true
                }
                R.id.navigation_cart -> true

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

    private fun setupRecycler() {
        adapter = CartAdapter(
            CartManager.getCart().toList().toMutableList()
        ) {
            updateSummary()
        }

        binding.recyclerCartItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerCartItems.adapter = adapter

        updateSummary()
    }


    private fun updateSummary() {
        val items = CartManager.getCart()
        val quantity = items.values.sum()
        val itemTotal = items.entries.sumOf { (it.key.price.replace("\\D".toRegex(), "").toIntOrNull() ?: 0) * it.value }
        val delivery = 1000
        val total = itemTotal + delivery

        binding.textView16.text = quantity.toString()
        binding.textView20.text = "$itemTotal tg"
        binding.textView18.text = "$delivery tg"
        binding.textView23.text = "$total tg"
    }

    private fun showOrderForm() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_order_form, null)
        val dialog = AlertDialog.Builder(this).setView(view).create()

        val name = view.findViewById<EditText>(R.id.input_name)
        val phone = view.findViewById<EditText>(R.id.input_phone)
        val address = view.findViewById<EditText>(R.id.input_address)
        val group = view.findViewById<RadioGroup>(R.id.payment_method_group)
        val cardDetails = view.findViewById<LinearLayout>(R.id.card_details_section)

        group.setOnCheckedChangeListener { _, checkedId ->
            cardDetails.visibility = if (checkedId == R.id.radio_card) LinearLayout.VISIBLE else LinearLayout.GONE
        }

        view.findViewById<Button>(R.id.btn_confirm_order).setOnClickListener {
            if (name.text.isNotEmpty() && phone.text.isNotEmpty() && address.text.isNotEmpty()) {
                val orderDao = com.example.qazstore.Data.AppDatabase.getDatabase(this).orderDao()
                val cartItems = CartManager.getCart()

                for ((product, _) in cartItems) {
                    val order = com.example.qazstore.Data.OrderEntity(
                        title = product.title,
                        price = product.price,
                        imageResId = product.imageResId
                    )

                    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
                        orderDao.insertOrder(order)
                    }
                }

                CartManager.clear()
                setupRecycler()
                dialog.dismiss()

                Toast.makeText(this, "Zakaz qabyldandy", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, HistoryActivity::class.java))
            } else {
                Toast.makeText(this, "Barlyk oristerdi toltyrynyz", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

}