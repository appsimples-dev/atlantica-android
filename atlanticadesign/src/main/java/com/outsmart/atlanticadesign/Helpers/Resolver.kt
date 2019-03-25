package com.outsmart.atlanticadesign.Helpers

import android.content.Context
import android.content.res.TypedArray
import android.util.TypedValue

class Resolver(
    private val context: Context,
    private val input: TypedArray
) {

    fun getResolvedDimenStyle(themeAttrId: Int, viewAttrId: Int, defValue: Int = 0): Int {
        val hasFromInput = input.hasValue(viewAttrId)
        if (hasFromInput) {
            return input.getDimensionPixelSize(viewAttrId, defValue)
        }
        val typedValue = TypedValue()
        val resolvedFromTheme = context.theme.resolveAttribute(themeAttrId, typedValue, true)
        if (resolvedFromTheme) {
            return typedValue.getDimension(context.resources.displayMetrics).toInt()
        }
        return defValue
    }

    fun getResolvedColorStyle(themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
        val hasFromInput = input.hasValue(viewAttrId)
        if (hasFromInput) {
            return input.getColor(viewAttrId, defValue)
        }
        val typedValue = TypedValue()
        val resolvedFromTheme = context.theme.resolveAttribute(themeAttrId, typedValue, true)
        if (resolvedFromTheme) {
            return typedValue.data
        }
        return defValue
    }

    fun getResolvedResIdStyle(themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
        val hasFromInput = input.hasValue(viewAttrId)
        if (hasFromInput) {
            return input.getResourceId(viewAttrId, defValue)
        }
        val typedValue = TypedValue()
        val resolvedFromTheme = context.theme.resolveAttribute(themeAttrId, typedValue, true)
        if (resolvedFromTheme) {
            return typedValue.data
        }
        return defValue
    }

    fun getResolvedEnumStyle(themeAttrId: Int, viewAttrId: Int, defValue: Int): Int {
        val hasFromInput = input.hasValue(viewAttrId)
        if (hasFromInput) {
            return input.getInteger(viewAttrId, defValue)
        }
        val typedValue = TypedValue()
        val resolvedFromTheme = context.theme.resolveAttribute(themeAttrId, typedValue, true)
        if (resolvedFromTheme) {
            return typedValue.data
        }
        return defValue
    }
}