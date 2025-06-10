package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.qazstore.R
import com.example.qazstore.databinding.ActivityRegistrationBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regTxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signUpButton.setOnClickListener {
            val login = binding.loginTxt.text.toString().trim()
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()
            val repeatPassword = binding.confirmPasswordTxt.text.toString().trim()

            if (login.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(this, "Барлық өрістерді толтырыңыз", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != repeatPassword) {
                Toast.makeText(this, "Құпия сөздер сәйкес емес", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            registerUser(login, email, password)
        }
    }

    private fun registerUser(login: String, email: String, password: String) {
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("login", login)
            .add("email", email)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url("http://10.0.2.2:8888/qazstore/register.php")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@RegistrationActivity, "Serverge kosyla almadym", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseText = response.body?.string()
                runOnUiThread {
                    when (responseText?.trim()) {
                        "success" -> {
                            Toast.makeText(this@RegistrationActivity, "Tirkelu satti!", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        "exists" -> Toast.makeText(this@RegistrationActivity, "Byl login tirkelgen", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(this@RegistrationActivity, "Kate: $responseText", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}