package com.example.workouts.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Workout")
data class WorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    val workoutId: Int = 0,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "duration")
    val duration: Long,
)
