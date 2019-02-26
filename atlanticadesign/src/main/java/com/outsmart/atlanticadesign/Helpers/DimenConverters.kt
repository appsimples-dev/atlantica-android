package com.outsmart.atlanticadesign.Helpers

import android.content.res.Resources


fun convertPixelsToDp(px: Int): Float {
    val metrics = Resources.getSystem().getDisplayMetrics()
    val dp = px / (metrics.densityDpi / 160f)
    return Math.round(dp).toFloat()
}

fun convertDpToPixel(dp: Int): Int {
    val metrics = Resources.getSystem().getDisplayMetrics()
    val px = dp * (metrics.densityDpi / 160f)
    return Math.round(px)
}