package com.example.workouts.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkoutWithSeriesEntity(
    @Embedded val workout: WorkoutEntity,
    @Relation(
        parentColumn = "workout_id",
        entityColumn = "exercise_id",
        associateBy = Junction(WorkoutHasExerciseEntity::class)
    )
    val sets: List<SeriesWithExerciseEntity>,
)
