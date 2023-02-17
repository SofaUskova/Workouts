package com.example.workouts.ui.start_workout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workouts.R
import com.example.workouts.ui.models.Series
import com.google.android.material.textfield.TextInputEditText

class SetListAdapter(
    private val list: MutableList<Series>,
) : RecyclerView.Adapter<SetListAdapter.ViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.card_set, parent, false)

        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            count.text = "${position + 1}"

            sets.setText(list[position].repeat.toString())
            weight.setText(list[position].weight)

            closeIcon.setOnClickListener {
//                notifyItemRemoved(position)
//                notifyDataSetChanged() //todo
//                notifyItemChanged(position)
                // notifyItemRemoved(position)
                list.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
//                notifyItemRangeChanged(position, list.size)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val count: TextView = itemView.findViewById(R.id.count)
        val sets: TextInputEditText = itemView.findViewById(R.id.sets)
        val weight: TextInputEditText = itemView.findViewById(R.id.weight)
        val closeIcon: ImageView = itemView.findViewById(R.id.closeIcon)
    }

}