package com.example.workouts.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workout(
    val workoutId: Int,
    val date: String,
    val duration: Long,
    val exercises: List<Exercise>? = null,
) : Parcelable

@Parcelize
data class Exercise(
    val name: String,
    val sets: List<Series>,
) : Parcelable

@Parcelize
data class Series(
    val repeat: Long = 0,
    val weight: String = "",
) : Parcelable
