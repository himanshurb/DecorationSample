package com.example.decorationsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomDividerDecoration(val context: Context, private val items: List<ListItem>)
    : RecyclerView.ItemDecoration() {

    private val dividerOffset = convertDpToPixel(16f, context)
    private val dividerHeight = convertDpToPixel(2f, context)

    private val paint = Paint()

    init {
        paint.color = ContextCompat.getColor(context, android.R.color.black)
        paint.strokeWidth = dividerHeight
    }

    private val mBounds: Rect = Rect()

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()

        val childCount = parent.childCount
        for (childIndex in 0 until childCount) {
            val child = parent.getChildAt(childIndex)

            parent.getDecoratedBoundsWithMargins(child, mBounds)

            var leftOffset = dividerOffset.toInt()

            if(childIndex > 0 && childIndex < childCount - 1 && items[childIndex + 1].isSection()) {
                leftOffset = 0
            }

            canvas.drawLine(
                    child.left.toFloat() + leftOffset,
                    child.bottom.toFloat(),
                    child.right.toFloat(),
                    child.bottom.toFloat(),
                    paint)
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.set(0, 0, 0, dividerHeight.toInt())
    }
}