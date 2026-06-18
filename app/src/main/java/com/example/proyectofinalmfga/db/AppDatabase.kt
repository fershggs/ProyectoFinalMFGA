package com.example.proyectofinalmfga.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.proyectofinalmfga.converters.Converters
import com.example.proyectofinalmfga.dao.PlayerDao
import com.example.proyectofinalmfga.model.Player

@Database(entities = [Player::class], version= 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun PlayerDao(): PlayerDao
}
