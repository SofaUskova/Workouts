package com.example.workouts.db.entities

import androidx.room.*

@Entity(
    tableName = "Series",
    foreignKeys = [
        ForeignKey(
            entity = ExerciseEntity::class,
            parentColumns = arrayOf("exercise_id"),
            childColumns = arrayOf("exercise_id"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class SeriesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "set_id")
    val setId: Int = 0,
    @ColumnInfo(name = "repeat")
    val repeat: Int,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "exercise_id", index = true)
    val exerciseId: Int,
)

@DatabaseView(
    "SELECT Exercise.exercise_id, Exercise.name, Series.repeat, Series.weight FROM Series " +
            "INNER JOIN Exercise " +
            "ON Series.exercise_id = Exercise.exercise_id"
)
data class SeriesWithExerciseEntity (
    @ColumnInfo(name = "exercise_id")
    val exerciseId: Int,
    val name: String,
    val repeat: Long,
    val weight: String,
)
