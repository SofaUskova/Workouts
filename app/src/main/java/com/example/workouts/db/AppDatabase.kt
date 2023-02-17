package com.example.workouts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workouts.db.dao.*
import com.example.workouts.db.entities.*

@Database(
    entities = [
        ExerciseEntity::class,
        SeriesEntity::class,
        WorkoutEntity::class,
        WorkoutHasExerciseEntity::class,
    ],
    views = [
        SeriesWithExerciseEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun seriesDao(): SeriesDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutHasExerciseDao(): WorkoutHasExerciseDao

    companion object {
        private const val TAG = "workouts.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, TAG).build()
        }
    }

}