package com.alex.tiendaonline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.alex.tiendaonline.model.User
import com.alex.tiendaonline.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializar ViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(UserViewModel::class.java)

        val btnLoginSubmit = findViewById<Button>(R.id.btnLoginSubmit)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnLoginSubmit.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(email, password,
                onSuccess = { user ->
                    // Guardar sesión en SharedPreferences
                    val prefs = getSharedPreferences("user_session", Context.MODE_PRIVATE)
                    prefs.edit()
                        .putInt("user_id", user.id)
                        .putString("role", user.role)
                        .apply()

                    // Navegar según el rol
                    val intent = if (user.role == "admin") {
                        Intent(this, AdminProductListActivity::class.java)
                    } else {
                        Intent(this, ProductListActivity::class.java)
                    }
                    startActivity(intent)
                    finish()
                },
                onError = {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}