package com.example.decorationsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val list: List<ListItem> = ArrayList()):
        RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when(ViewType.values()[viewType]) {
            ViewType.SECTION -> ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_section_item,
                            parent, false)
            )

            ViewType.NORMAL -> ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.list_item,
                            parent, false)
            )
        }
    }
    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        val item = this.list[position]
        return if (item.isSection()) {
            ViewType.SECTION.ordinal
        } else {
            ViewType.NORMAL.ordinal
        }
    }

    private enum class ViewType {
        SECTION,
        NORMAL
    }
}