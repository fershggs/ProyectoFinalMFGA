package com.example.proyectofinalmfga.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyectofinalmfga.model.Player

@Dao
interface PlayerDao {
    @Insert
    suspend fun registerPlayer(player: Player)

    @Query("SELECT * FROM  player_table WHERE name = :usuario AND password = :contrasena LIMIT 1")
    suspend fun loginPlayer(usuario: String, contrasena: String): Player?
}