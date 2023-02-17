package com.example.workouts.db.entities

import androidx.room.*

@Entity(tableName = "Exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int = 0,
    @ColumnInfo(name = "name", index = true)
    val name: String,
)
