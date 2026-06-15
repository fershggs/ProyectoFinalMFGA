package com.example.proyectofinalmfga

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.proyectofinalmfga.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.toString

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = MyApplication.getDatabase(this)
        val jugadoraDao = db.JugadoraDao()


        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val nombreInput = binding.ettUser.text.toString().trim()
            val contrasenaInput = binding.ettPassword.text.toString().trim()

            // Validación simple de campos vacíos antes de ir a la BD
            if (nombreInput.isEmpty() || contrasenaInput.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                val jugadoraEncontrada = jugadoraDao.loginJugadora(nombreInput, contrasenaInput)

                withContext(Dispatchers.Main) {
                    if (jugadoraEncontrada != null) {

                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                        //Esto para cambiar el jugadoraEncontrada.nombre por la variable real que contenga el nombre
                        intent.putExtra("EXTRA_JUGADORA", jugadoraEncontrada.nombre)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}