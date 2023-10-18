package com.example.midtermapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScoreDao {
    @Query("SELECT * FROM Score")
    fun queryScoreList(): List<Score?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScore(score: Score?)
}