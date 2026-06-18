package com.example.proyectofinalmfga.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinalmfga.model.Jugadora

@Dao
interface JugadoraDao {
    @Insert
    suspend fun registrarJugadora(jugadora: Jugadora)

    @Query("SELECT * FROM jugadoras_table WHERE nombre = :usuario AND password = :contrasena LIMIT 1")
    suspend fun loginJugadora(usuario: String, contrasena: String): Jugadora?
}