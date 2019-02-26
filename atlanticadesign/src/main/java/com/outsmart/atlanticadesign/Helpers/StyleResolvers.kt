package com.outsmart.atlanticadesign.Helpers

import android.content.Context
import android.content.res.Resources.Theme
import android.content.res.TypedArray
import android.util.TypedValue

fun resolveDimenStyle(context: Context, input: TypedArray, themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
    val hasFromInput = input.hasValue(viewAttrId)
    if (hasFromInput) {
        return input.getDimensionPixelSize(viewAttrId, defValue)
    }
    val typedValue = TypedValue()
    val resolvedFromTheme  = context.theme.resolveAttribute(themeAttrId, typedValue, true)
    if (resolvedFromTheme) {
        return typedValue.getDimension(context.resources.displayMetrics).toInt()
    }
    return defValue
}

fun resolveColorStyle(context: Context, input: TypedArray, themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
    val hasFromInput = input.hasValue(viewAttrId)
    if (hasFromInput) {
        return input.getColor(viewAttrId, defValue)
    }
    val typedValue = TypedValue()
    val resolvedFromTheme  = context.theme.resolveAttribute(themeAttrId, typedValue, true)
    if (resolvedFromTheme) {
        return typedValue.data
    }
    return defValue
}

fun resolveResIdStyle(context: Context, input: TypedArray, themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
    val hasFromInput = input.hasValue(viewAttrId)
    if (hasFromInput) {
        return input.getResourceId(viewAttrId, defValue)
    }
    val typedValue = TypedValue()
    val resolvedFromTheme  = context.theme.resolveAttribute(themeAttrId, typedValue, true)
    if (resolvedFromTheme) {
        return typedValue.data
    }
    return defValue
}

fun resolveEnumStyle(context: Context, input: TypedArray, themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
    val hasFromInput = input.hasValue(viewAttrId)
    if (hasFromInput) {
        return input.getInteger(viewAttrId, defValue)
    }
    val typedValue = TypedValue()
    val resolvedFromTheme  = context.theme.resolveAttribute(themeAttrId, typedValue, true)
    if (resolvedFromTheme) {
        return typedValue.data
    }
    return defValue
}