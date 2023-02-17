package com.example.workouts.ui.next_workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workouts.R
import com.example.workouts.db.AppDatabase
import com.example.workouts.ui.start_workout.StartWorkout
import kotlinx.android.synthetic.main.activity_next_workout.*

class NextWorkout : AppCompatActivity() {
    private val viewModel: NextWorkoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_workout)

        val db = AppDatabase(this)

//        viewModel.setData(db)
        viewModel.getWorkout(db,1)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ListAdapter(
            onItemClickListener = object : OnItemClickListener {
                override fun onItemClicked(position: Int, any: Any?) {}
            }
        )
        recyclerView.adapter = adapter

        viewModel.currentWorkout.observe(this) { newData ->
            adapter.setData(newData.exercises?.toMutableList())
        }

        startWorkout.setOnClickListener {
            startActivity(Intent(this, StartWorkout::class.java))
        }
    }

}