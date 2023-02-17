package com.example.workouts.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "Workout_has_Exercise",
    primaryKeys = ["workout_id", "exercise_id"]
)
data class WorkoutHasExerciseEntity(
    @ColumnInfo(name = "workout_id")
    val workoutId: Int,
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,
)
