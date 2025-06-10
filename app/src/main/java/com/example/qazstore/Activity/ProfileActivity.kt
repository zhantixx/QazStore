package com.example.qazstore.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qazstore.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.bumptech.glide.Glide
import com.example.qazstore.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val EDIT_PROFILE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
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
                R.id.navigation_profile -> true
                else -> false
            }
        }

        binding.konfImg.setOnClickListener {
            startActivity(Intent(this, PrivacyActivity::class.java))
        }

        binding.merzimImg.setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }

        binding.editImg.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivityForResult(intent, EDIT_PROFILE_REQUEST)
        }
        binding.outImg.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == EDIT_PROFILE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val name = data.getStringExtra("name")
            val email = data.getStringExtra("email")
            val imageUri = data.getStringExtra("imageUri")

            binding.textView2.text = name
            binding.textView3.text = email

            if (!imageUri.isNullOrEmpty()) {
                Glide.with(this).load(imageUri).into(binding.editImg)
            }
        }
    }
}
