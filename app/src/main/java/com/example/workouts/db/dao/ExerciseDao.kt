package com.example.workouts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.workouts.db.entities.ExerciseEntity

@Dao
interface ExerciseDao {

    @Transaction
    @Query("SELECT * FROM Exercise")
    suspend fun getAllExercises(): List<ExerciseEntity>

    @Insert
    fun insertExercises(vararg exerciseEntities: ExerciseEntity)

}