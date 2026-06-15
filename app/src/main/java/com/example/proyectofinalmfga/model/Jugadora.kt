package com.example.proyectofinalmfga.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("jugadoras_table")
data class Jugadora (
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val nombre: String,
    val password: String
)