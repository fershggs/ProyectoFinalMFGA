package com.example.proyectofinalmfga.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("player_table")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val name: String,
    val password: String
)