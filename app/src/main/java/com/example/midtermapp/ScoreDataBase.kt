package com.example.midtermapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreDataBase : RoomDatabase() {
    abstract fun scoreDao(): ScoreDao?
}