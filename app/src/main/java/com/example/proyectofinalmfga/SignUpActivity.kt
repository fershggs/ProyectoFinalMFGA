package com.example.proyectofinalmfga

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.proyectofinalmfga.databinding.ActivitySignUpBinding
import com.example.proyectofinalmfga.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        val playerDao = db.PlayerDao()

        binding.btnSURegister.setOnClickListener {
            val nameInput = binding.ettSUUser.text.toString()
            val passwordInput = binding.ettSUPassword.text.toString()
            if (nameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                val jugadorExistente = playerDao.loginPlayer(nameInput, passwordInput)
                if (jugadorExistente != null) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignUpActivity, "Ya está registrado", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val nuevaPlayer = Player(name = nameInput, password = passwordInput)
                    playerDao.registerPlayer(nuevaPlayer)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@SignUpActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}