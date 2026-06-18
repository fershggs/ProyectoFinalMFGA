package com.example.proyectofinalmfga

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectofinalmfga.databinding.ActivityMatchesBinding

class MatchesActivity : AppCompatActivity() {

    lateinit var binding : ActivityMatchesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchesBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvwMatches.layoutManager = LinearLayoutManager(this)
        binding.rvwMatches.adapter = MatchAdapter(getMatches())
    }

    //función Equivalente a getNews()
    fun getMatches(): List<Match> {
        return listOf(
            Match("Tigres UANL", "15/11/2023 - 19:00", "Estadio Universitario"),
            Match("Rayadas", "22/11/2023 - 21:00", "Estadio BBVA"),
            Match("América Femenil", "29/11/2023 - 17:00", "Cancha Centenario"),
            Match("Chivas", "05/12/2023 - 18:00", "Estadio Akron"),
            Match("Pachuca", "12/12/2023 - 20:00", "Estadio Hidalgo"),
            Match("Cruz Azul", "19/12/2023 - 16:00", "Instalaciones La Noria"),
            Match("Pumas UNAM", "09/01/2024 - 12:00", "Estadio Olímpico Universitario"),
            Match("Toluca", "16/01/2024 - 17:00", "Estadio Nemesio Díez")
        )
    }
}