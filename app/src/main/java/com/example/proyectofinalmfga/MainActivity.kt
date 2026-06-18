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
        val playerDao = db.PlayerDao()


        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val nameInput = binding.ettUser.text.toString().trim()
            val passwordInput = binding.ettPassword.text.toString().trim()

            if (nameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                val playerFound = playerDao.loginPlayer(nameInput, passwordInput)

                withContext(Dispatchers.Main) {
                    if (playerFound != null) {

                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)

                        intent.putExtra("EXTRA_JUGADORA", playerFound.name)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}