package com.example.decorationsample

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import kotlinx.android.synthetic.main.list_section_item.view.*

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val sectionView: TextView? = itemView.textSection
    private val cellView: TextView = itemView.titleSubtitleItem
    val context: Context = cellView.context

    fun bind(productDetailItem: ListItem) {
        val builder = SpannableStringBuilder()
        builder.append(productDetailItem.title)

        productDetailItem.subtitle?.let {
            builder.append("\n")
            val startPosition = builder.length
            builder.append(it)
            builder.setSpan(TextAppearanceSpan(context, R.style.ListItemSubtitle),
                    startPosition, builder.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }

        sectionView?.apply {
            text = productDetailItem.sectionHeader
        }

        cellView.text = builder
    }
}