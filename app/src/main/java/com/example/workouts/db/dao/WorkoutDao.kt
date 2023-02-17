package com.example.workouts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.workouts.db.entities.SeriesInExerciseEntity
import com.example.workouts.db.entities.WorkoutEntity
import com.example.workouts.db.entities.WorkoutWithSeriesEntity

@Dao
interface WorkoutDao {

    @Insert
    fun insertAll(vararg workoutEntity: WorkoutEntity)

    @Query("SELECT * FROM Workout WHERE workout_id = :workoutId")
    fun getWorkoutById(workoutId: Int): WorkoutEntity

    @Transaction
    @Query("SELECT * FROM Exercise")
    fun getExerciseWithSeries(): List<SeriesInExerciseEntity>

    @Transaction
    @Query("SELECT * FROM Workout WHERE workout_id = :workoutId")
    fun getWorkoutWithSeriesById(workoutId: Int): WorkoutWithSeriesEntity

}