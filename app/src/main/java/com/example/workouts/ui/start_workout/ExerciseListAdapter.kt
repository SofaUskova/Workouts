package com.example.workouts.ui.start_workout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workouts.R
import com.example.workouts.ui.next_workout.OnItemClickListener

class ExerciseListAdapter(
    private val context: Context,
    private val list: List<String>,
    private val onItemClickListener: OnItemClickListener
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
            val listOfSets: MutableList<String> = mutableListOf("", "", "")
            val setAdapter = SetListAdapter(listOfSets)
            setRecyclerView.layoutManager = LinearLayoutManager(context)
            setRecyclerView.adapter = setAdapter

            title.setOnClickListener {
                val cardIsClose = addSetBtn.visibility == View.GONE

                addSetBtn.visibility = if (cardIsClose) View.VISIBLE else View.GONE
                cardIcon.setImageResource(if (cardIsClose) R.drawable.ic_arrow_right else R.drawable.ic_arrow_down)
                setRecyclerView.visibility = if (cardIsClose) View.VISIBLE else View.GONE
            }

            addSetBtn.setOnClickListener {
                listOfSets.add("")
                setAdapter.notifyItemInserted(listOfSets.size - 1)
            }

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardExerciseWithEdit)
        val title: LinearLayout = itemView.findViewById(R.id.title)
        val setRecyclerView: RecyclerView = itemView.findViewById(R.id.setRecyclerView)
        val addSetBtn: Button = itemView.findViewById(R.id.addSet)
        val cardIcon: ImageView = itemView.findViewById(R.id.cardIcon)
    }

}