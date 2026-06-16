package com.example.proyectofinalmfga

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        // Inicializar SharedPreferences
        val sharedPreferences = getSharedPreferences("PreferenciaConexiones", Context.MODE_PRIVATE)
        // Se crea una clave única por jugadora (ej: "CONEXION_Ana")
        val llaveUsuario = "CONEXION_$nombreJugadora"
        // 4Leer la última conexión guardada antes de actualizarla
        val ultimaConexionGuardada = sharedPreferences.getString(llaveUsuario, "Primera vez que inicias sesión")

        tvwConection.text = "Última conexión registrada:\n$ultimaConexionGuardada"

        // acutalizar: Obtener la fecha y hora de este instante preciso
        val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaActualTexto = formatoFecha.format(Date())

        // Guardar la nueva fecha en SharedPreferences para la próxima vez que inicie sesión
        val editor = sharedPreferences.edit()
        editor.putString(llaveUsuario, fechaActualTexto)
        editor.apply() // Se guarda de forma asíncrona en segundo plano

        val btnVerPartidos = findViewById<Button>(R.id.btnPartidos)

        btnVerPartidos.setOnClickListener {
            // El Intent es el mensajero. Le decimos: "Ve de esta pantalla (this) a PartidosActivity"
            val intent = Intent(this, MatchesActivity::class.java)
            startActivity(intent)
        }
    }
}