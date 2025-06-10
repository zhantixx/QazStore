package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.qazstore.R
import com.example.qazstore.databinding.ActivityPrivacyBinding
import com.example.qazstore.databinding.ActivityTermsBinding

class TermsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTermsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn2.setOnClickListener {
            startActivity(Intent(this@TermsActivity, ProfileActivity::class.java))
        }


    }
}