package com.example.midtermapp

import android.app.Application
import androidx.room.Room.databaseBuilder

class MainApplication : Application() {
    var scoreDataBase: ScoreDataBase? = null
        private set
    var NAME = ""
    override fun onCreate() {
        super.onCreate()
        instance = this
        scoreDataBase = databaseBuilder(instance!!, ScoreDataBase::class.java, "Score")
            .addMigrations()
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}