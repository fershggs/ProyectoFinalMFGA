package com.example.proyectofinalmfga

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.proyectofinalmfga.databinding.ActivitySignUpBinding
import com.example.proyectofinalmfga.model.Jugadora
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.toString

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = MyApplication.getDatabase(this)
        val jugadoraDao = db.JugadoraDao()

        binding.btnSURegister.setOnClickListener {
            val nombreInput = binding.ettSUUser.text.toString()
            val contrasenaInput = binding.ettSUPassword.text.toString()
            // Validación: Evitar que guarden registros vacíos
            if (nombreInput.isEmpty() || contrasenaInput.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val jugadora = Jugadora(0, nombreInput, contrasenaInput)

            // Inserción directa en la base de datos en segundo plano
            lifecycleScope.launch(Dispatchers.IO) {
                jugadoraDao.registrarJugadora(jugadora)
            }
        }
    }
}