package com.example.workouts.ui.models

data class Workout(
    val workoutId: Int,
    val date: String,
    val duration: Long,
    val exercises: List<Exercise>? = null,
)

data class Exercise(
    val name: String,
    val sets: List<Series>,
)

data class Series(
    val repeat: Long,
    val weight: String,
)
