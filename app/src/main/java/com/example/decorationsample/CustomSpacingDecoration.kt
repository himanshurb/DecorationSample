package com.example.decorationsample

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomSpacingDecoration(val context: Context, private val items: List<ListItem>)
    : RecyclerView.ItemDecoration() {

    private val sectionOffset = convertDpToPixel(24f, context)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = state.itemCount
        val itemPosition = parent.getChildAdapterPosition(view)
        val item = items[itemPosition]

        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        var top = outRect.top
        var bottom = outRect.bottom

        // Add offset for all non-first sections
        if(itemPosition > 0 && item.isSection()) {
            top = sectionOffset.toInt()
        }

        if(itemPosition == itemCount - 1) {
            bottom = sectionOffset.toInt()
        }

        outRect.set(outRect.left, top, outRect.right, bottom)
    }
}