package com.example.workouts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workouts.db.entities.WorkoutHasExerciseEntity

@Dao
interface WorkoutHasExerciseDao {

    @Insert
    fun insertAll(vararg workoutHasExerciseEntity: WorkoutHasExerciseEntity)

}