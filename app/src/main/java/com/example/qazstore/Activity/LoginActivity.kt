package com.example.qazstore.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.qazstore.R
import com.example.qazstore.databinding.ActivityHomeBinding
import com.example.qazstore.databinding.ActivityLoginBinding
import okhttp3.*
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginEditText = binding.loginTxt
        val passwordEditText = binding.passwrodTxt
        val loginButton = binding.signInButton



        binding.regTxt.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {
                loginUser(login, password)
            } else {
                Toast.makeText(this, "Barlıq orister toltyralmagan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(login: String, password: String) {
        val client = OkHttpClient()

        val requestBody = FormBody.Builder()
            .add("login", login)
            .add("password", password)
            .build()


        val request = Request.Builder()
            .url("http://10.0.2.2:8888/deliverygo/login.php")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Serverge qosyla almadym", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseText = response.body?.string()

                    runOnUiThread {
                        if (responseText == "success") {
                            Toast.makeText(this@LoginActivity, "Kiru satti", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login nemese qupia soz qate", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Serverden jauap ala almadym", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}