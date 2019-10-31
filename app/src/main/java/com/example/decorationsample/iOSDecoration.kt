package com.example.decorationsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class iOSDecoration(context: Context, private val items: List<ListItem>)
    : RecyclerView.ItemDecoration() {

    private val sectionOffset = convertDpToPixel(24f, context)
    private val sectionElevation = convertDpToPixel(2f, context)
    private val dividerOffset = convertDpToPixel(16f, context)

    private val divider: Drawable?
    private val layerDrawable: LayerDrawable?

    private val bounds: Rect = Rect()
    private val dividerHeight: Int

    init {
        val typedArray = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        divider = typedArray.getDrawable(0)
        typedArray.recycle()

        dividerHeight = divider?.intrinsicHeight ?: 0

        val whiteDivider = ShapeDrawable(RectShape())
        whiteDivider.paint.color = ContextCompat.getColor(context, android.R.color.white)
        whiteDivider.intrinsicHeight = dividerHeight
        val insetDivider = InsetDrawable(divider, dividerOffset.toInt(), 0, 0, 0)
        layerDrawable = LayerDrawable(arrayOf(whiteDivider, insetDivider))
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        canvas.save()

        val left: Int
        val right: Int

        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount
        val itemCount = state.itemCount
        for (childIndex in 0 until childCount) {
            val child = parent.getChildAt(childIndex)
            ViewCompat.setElevation(child, 0f)

            parent.getDecoratedBoundsWithMargins(child, bounds)
            val adapterPosition = parent.getChildAdapterPosition(child)
            var bottom = bounds.bottom + child.translationY.roundToInt()

            if(adapterPosition > 0 && adapterPosition < itemCount - 1
                && items[adapterPosition + 1].isSection()) {

                ViewCompat.setElevation(child, sectionElevation)

                divider?.apply {
                    val top = bottom - intrinsicHeight + sectionOffset.toInt()
                    bottom += sectionOffset.toInt()
                    setBounds(left, top, right, bottom)
                    draw(canvas)
                }

            } else if(adapterPosition == itemCount -1) {
                ViewCompat.setElevation(child, sectionElevation)

            } else {
                layerDrawable?.apply {
                    val top = bottom - intrinsicHeight
                    setBounds(left, top, right, bottom)
                    draw(canvas)
                }
            }
        }
        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (divider == null) {
            outRect.set(0, 0, 0, 0)
            return
        }

        outRect.set(0, 0, 0, dividerHeight)

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