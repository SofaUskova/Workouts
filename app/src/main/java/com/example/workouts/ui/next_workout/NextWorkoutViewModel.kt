package com.example.workouts.ui.next_workout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouts.db.AppDatabase
import com.example.workouts.db.entities.*
import com.example.workouts.ui.models.Exercise
import com.example.workouts.ui.models.Series
import com.example.workouts.ui.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NextWorkoutViewModel : ViewModel() {
    var currentWorkout = MutableLiveData<Workout>()

    fun setData(db: AppDatabase) {
        setExercisesListData(db)
    }

    private fun setExercisesListData(db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val exercises = listOf(
                    ExerciseEntity(name = "Классические отжимания"),
                    ExerciseEntity(name = "Отжимания на трицепс"),
                    ExerciseEntity(name = "Приседания"),
                    ExerciseEntity(name = "Мертвая тяга"),
                    ExerciseEntity(name = "Доброе утро")
                )
                db.exerciseDao().insertExercises(*exercises.toTypedArray())
            }
        }.invokeOnCompletion {
            setSeriesListData(db)
        }
    }

    private fun setSeriesListData(db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val series = listOf(
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 1),
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 1),
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 1),
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 2),
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 2),
                    SeriesEntity(repeat = 15, weight = 0, exerciseId = 2),
                    SeriesEntity(repeat = 12, weight = 40, exerciseId = 3),
                    SeriesEntity(repeat = 12, weight = 40, exerciseId = 3),
                    SeriesEntity(repeat = 12, weight = 40, exerciseId = 3),
                    SeriesEntity(repeat = 15, weight = 40, exerciseId = 4),
                    SeriesEntity(repeat = 15, weight = 60, exerciseId = 4),
                    SeriesEntity(repeat = 15, weight = 60, exerciseId = 4),
                    SeriesEntity(repeat = 15, weight = 20, exerciseId = 5),
                    SeriesEntity(repeat = 15, weight = 40, exerciseId = 5),
                    SeriesEntity(repeat = 15, weight = 40, exerciseId = 5),
                )
                db.seriesDao().insertAll(*series.toTypedArray())
            }
        }.invokeOnCompletion {
            setWorkoutsListData(db)
        }
    }

    private fun setWorkoutsListData(db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val workouts = listOf(
                    WorkoutEntity(date = "02.02.2023", duration = 60),
                )
                db.workoutDao().insertAll(*workouts.toTypedArray())
            }
        }.invokeOnCompletion {
            joinWorkoutWithExercises(db)
        }
    }

    private fun joinWorkoutWithExercises(db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val workoutWithExercises = mutableListOf<WorkoutHasExerciseEntity>().apply {
                    this.add(WorkoutHasExerciseEntity(workoutId = 1, exerciseId = 1))
                    this.add(WorkoutHasExerciseEntity(workoutId = 1, exerciseId = 2))
                    this.add(WorkoutHasExerciseEntity(workoutId = 1, exerciseId = 3))
                    this.add(WorkoutHasExerciseEntity(workoutId = 1, exerciseId = 4))
                    this.add(WorkoutHasExerciseEntity(workoutId = 1, exerciseId = 5))
                }

                db.workoutHasExerciseDao().insertAll(*workoutWithExercises.toTypedArray())
            }
        }
    }

    fun getWorkout(db: AppDatabase, workoutId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val workoutEntity = db.workoutDao().getWorkoutWithSeriesById(workoutId)

                val exercises = workoutEntity.sets
                    .distinctBy { it.name }
                    .map { set ->
                        Exercise(
                            name = set.name,
                            sets = workoutEntity.sets
                                .filter { it.name == set.name }
                                .map { Series(it.repeat, it.weight) }
                        )
                    }

                val workout = Workout(
                    workoutId = workoutEntity.workout.workoutId,
                    date = workoutEntity.workout.date,
                    duration = workoutEntity.workout.duration,
                    exercises = exercises
                )

                currentWorkout.postValue(workout)
            }
        }
    }

}