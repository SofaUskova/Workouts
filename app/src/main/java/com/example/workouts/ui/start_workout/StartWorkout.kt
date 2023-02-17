package com.example.workouts.ui.start_workout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workouts.R
import com.example.workouts.ui.SwipeToDelete
import com.example.workouts.ui.next_workout.OnItemClickListener
import kotlinx.android.synthetic.main.activity_start_workout.*


class StartWorkout : AppCompatActivity() {

    var exerciseList = mutableListOf("", "", "", "", "", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_workout)

        val adapter = ExerciseListAdapter(
            context = this,
            list = exerciseList,
            onItemClickListener = object : OnItemClickListener {
                override fun onItemClicked(position: Int, any: Any?) {

                }
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        ItemTouchHelper(
            SwipeToDelete(
                snackBarBackgroundColor = resources.getColor(R.color.primary),
                backgroundColor = resources.getColor(R.color.secondary),
                deleteDrawable = resources.getDrawable(R.drawable.ic_trash),
                list = exerciseList,
                adapter = adapter,
                recyclerView = recyclerView
            )
        ).attachToRecyclerView(recyclerView)
    }

}