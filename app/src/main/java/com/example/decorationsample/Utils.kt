@file:JvmName("Utils")

package com.example.decorationsample

import android.content.Context
import android.util.DisplayMetrics

fun convertDpToPixel(dp: Float, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}