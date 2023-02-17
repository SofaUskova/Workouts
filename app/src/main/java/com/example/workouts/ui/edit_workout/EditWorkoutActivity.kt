package com.example.workouts.ui.edit_workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workouts.R
import com.example.workouts.ui.start_workout.ExerciseListAdapter
import kotlinx.android.synthetic.main.activity_start_workout.*

class EditWorkoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_workout)

        val adapter = ExerciseListAdapter(
            context = this,
            list = listOf(),
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}