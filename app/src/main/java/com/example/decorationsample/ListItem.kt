package com.example.decorationsample

data class ListItem(val title: String?, val subtitle: String?,
                    val sectionHeader: String? = null) {
    fun isSection() = !sectionHeader.isNullOrEmpty()
}