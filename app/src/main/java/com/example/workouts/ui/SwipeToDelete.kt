package com.example.workouts.ui

import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.workouts.ui.start_workout.ExerciseListAdapter
import com.google.android.material.snackbar.Snackbar

class SwipeToDelete(
    private val snackBarBackgroundColor: Int,
    private val backgroundColor: Int,
    private val deleteDrawable: Drawable,
    private val list: MutableList<String>,
    private val adapter: ExerciseListAdapter,
    private val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    private var background: PaintDrawable = PaintDrawable(backgroundColor)

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
        val itemView: View = viewHolder.itemView
        val itemHeight: Int = itemView.height
        val isCancelled = dX == 0f && !isCurrentlyActive

        if (isCancelled) {
            super.onChildDraw(
                c,
                recyclerView,
                viewHolder,
                dX,
                dY,
                actionState,
                isCurrentlyActive
            )
            return
        }

        background.setCornerRadius(40F)
        background.setBounds(
            itemView.left,
            itemView.top,
            itemView.right,
            itemView.bottom
        )
        background.draw(c)

        val intrinsicWidth = deleteDrawable.intrinsicWidth
        val intrinsicHeight = deleteDrawable.intrinsicHeight

        val deleteIconMargin = intrinsicHeight / 2
        val deleteIconTop: Int = itemView.top + (itemHeight - intrinsicHeight) / 2
        val deleteIconLeft: Int = itemView.left + deleteIconMargin
        val deleteIconRight: Int = itemView.left + deleteIconMargin + intrinsicWidth
        val deleteIconBottom = deleteIconTop + intrinsicHeight

        deleteDrawable.setBounds(
            deleteIconLeft,
            deleteIconTop,
            deleteIconRight,
            deleteIconBottom
        )
        deleteDrawable.draw(c)

        super.onChildDraw(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.7f
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val exerciseNumber = list[viewHolder.adapterPosition]
        val position = viewHolder.adapterPosition

        list.removeAt(viewHolder.adapterPosition)
        adapter.notifyItemRemoved(viewHolder.adapterPosition)

        Snackbar
            .make(recyclerView, "$exerciseNumber удалено", Snackbar.LENGTH_LONG)
            .setBackgroundTint(snackBarBackgroundColor)
            .setActionTextColor(Color.WHITE)
            .setAction("Отменить") {
                list.add(position, exerciseNumber)
                adapter.notifyItemInserted(position)
            }.show()
    }

}