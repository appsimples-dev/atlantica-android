package com.outsmart.atlanticadesign.Helpers

import android.content.res.Resources


fun convertPixelsToDp(px: Float): Float {
    val metrics = Resources.getSystem().getDisplayMetrics()
    val dp = px / (metrics.densityDpi / 160f)
    return Math.round(dp).toFloat()
}

fun convertDpToPixel(dp: Float): Int {
    val metrics = Resources.getSystem().getDisplayMetrics()
    val px = dp * (metrics.densityDpi / 160f)
    return Math.round(px)
}