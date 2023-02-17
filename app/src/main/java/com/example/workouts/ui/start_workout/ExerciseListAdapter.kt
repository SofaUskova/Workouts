package com.example.workouts.ui.start_workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouts.R
import com.example.workouts.ui.models.Exercise
import com.example.workouts.ui.models.Series

class ExerciseListAdapter(
    private val context: Context,
    private val list: List<Exercise>,
) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_exercise_with_edit, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val listOfSets: MutableList<Series> = list[position].sets.toMutableList()
            val setAdapter = SetListAdapter(listOfSets)
            setRecyclerView.layoutManager = LinearLayoutManager(context)
            setRecyclerView.adapter = setAdapter

            exerciseName.text = list[position].name
            title.setOnClickListener {
                setsList.visibility = if(isVisible(setsList)) View.GONE else View.VISIBLE
                cardIcon.setImageResource(if (isVisible(setsList)) R.drawable.ic_arrow_down else R.drawable.ic_arrow_right)
            }

            addSetBtn.setOnClickListener {
                listOfSets.add(Series())
                setAdapter.notifyItemInserted(listOfSets.size - 1)
            }

        }
    }

    private fun isVisible(view: View): Boolean = view.visibility == View.VISIBLE

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: LinearLayout = itemView.findViewById(R.id.title)
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val setRecyclerView: RecyclerView = itemView.findViewById(R.id.setRecyclerView)
        val setsList: LinearLayout = itemView.findViewById(R.id.setsList)
        val addSetBtn: Button = itemView.findViewById(R.id.addSet)
        val cardIcon: ImageView = itemView.findViewById(R.id.cardIcon)
    }

}