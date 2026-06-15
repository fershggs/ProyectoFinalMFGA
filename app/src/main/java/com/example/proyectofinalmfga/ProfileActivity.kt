package com.example.proyectofinalmfga

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvwPAName = findViewById<TextView>(R.id.tvwPAName)
        val tvwConection = findViewById<TextView>(R.id.tvwConection)
        val imvImage = findViewById<ImageView>(R.id.imvImage)

        val nombreJugadora = intent.getStringExtra("EXTRA_JUGADORA") ?: "Jugador"
        tvwPAName.text = nombreJugadora

        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaActual = formatoFecha.format(Date())

        tvwConection.text = "Última conexión: $fechaActual"

    }
}