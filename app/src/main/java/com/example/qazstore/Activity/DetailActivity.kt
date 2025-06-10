package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.qazstore.Data.AppDatabase
import com.example.qazstore.Data.CartManager
import com.example.qazstore.Domain.ProductModel
import com.example.qazstore.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {

    companion object {
        const val PRODUCT_KEY = "product" // используем везде одинаково
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Получаем товар из интента
        val product = intent.getSerializableExtra(PRODUCT_KEY) as? ProductModel
        if (product == null) {
            Toast.makeText(this, "Тауар табылмады", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Привязка View
        val titleTxt = findViewById<TextView>(R.id.titleTxt)
        val addressTxt = findViewById<TextView>(R.id.addressTxt)
        val descTxt = findViewById<TextView>(R.id.descTxt)
        val priceTxt = findViewById<TextView>(R.id.priceTxt)
        val imageView = findViewById<ImageView>(R.id.imageView10)
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val favBtn = findViewById<ImageView>(R.id.favBtn)
        val shareBtn = findViewById<ImageView>(R.id.shareBtn)
        val cartBtn = findViewById<TextView>(R.id.sebetBtn)

        // Заполняем данные
        titleTxt.text = product.title
        addressTxt.text = "Brand: ${product.brand}"
        descTxt.text = product.description
        priceTxt.text = product.price
        Glide.with(this).load(product.imageResId).into(imageView)

        // Назад
        backBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        // Добавить в избранное
        favBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(this@DetailActivity).favoriteDao().insertFavorite(product)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@DetailActivity, "Тауар таңдалды", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Поделиться
        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, product.title)
                putExtra(Intent.EXTRA_TEXT, "${product.title} - ${product.price} ₸")
            }
            startActivity(Intent.createChooser(intent, "Бөлісу"))
        }

        // Добавить в корзину
        cartBtn.setOnClickListener {
            CartManager.addToCart(product)
            Toast.makeText(this, "Себетке қосылды", Toast.LENGTH_SHORT).show()
        }
    }
}
