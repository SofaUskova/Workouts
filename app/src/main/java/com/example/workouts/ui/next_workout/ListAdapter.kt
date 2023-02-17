package com.example.workouts.ui.next_workout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.workouts.R
import com.example.workouts.ui.models.Exercise

class ListAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var list = mutableListOf<Exercise>()

    fun setData(newData: MutableList<Exercise>?) {
        list.addAll(newData ?: listOf())
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_exercise, parent, false)

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.cardView.setOnClickListener {
            onItemClickListener.onItemClicked(position, item)
        }

        holder.counter.text = "${position + 1}."
        holder.exerciseName.text = item.name
        holder.exerciseDescription.text = "${item.sets.size} подхода ${item.sets.maxBy { it.repeat }.repeat} повторений"
        holder.maxWeight.text = "${item.sets.maxBy { it.weight }.weight}кг"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardExercise)
        val counter: TextView = itemView.findViewById(R.id.count)
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)
        val exerciseDescription: TextView = itemView.findViewById(R.id.exerciseDescription)
        val maxWeight: TextView = itemView.findViewById(R.id.weight)
    }

}

interface OnItemClickListener {
    fun onItemClicked(position: Int, any: Any?)
}