package com.example.workouts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.workouts.db.entities.SeriesEntity

@Dao
interface SeriesDao {

    @Insert
    fun insertAll(vararg seriesEntities: SeriesEntity)

}