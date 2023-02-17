package com.example.workouts.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SeriesInExerciseEntity(
    @Embedded val exercise: ExerciseEntity,
    @Relation(
        parentColumn = "exercise_id",
        entityColumn = "exercise_id"
    )
    val sets: List<SeriesEntity>
)
