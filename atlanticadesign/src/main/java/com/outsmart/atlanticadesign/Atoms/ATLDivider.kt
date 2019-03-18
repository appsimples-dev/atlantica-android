package com.outsmart.atlanticadesign.Atoms

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import androidx.appcompat.widget.ContentFrameLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.outsmart.atlanticadesign.Helpers.Resolver
import com.outsmart.atlanticadesign.Helpers.convertDpToPixel
import com.outsmart.atlanticadesign.R

class ATLDivider @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {

    private var style: Style = Style()

    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLSettingsItem,
            0, 0
        ).apply {
            loadInitialStyle(this)
            recycle()
        }
        applyStyle()
    }

    private fun loadInitialStyle(input: TypedArray) {
        with(Resolver(context, input)) {
            style.apply {
                color = getResolvedColorStyle(R.attr.mediumGrayColor, 0, 0)
            }
        }
    }

    private fun applyStyle() {
        setBackgroundColor(style.color)
    }

    fun create(height: Int = 1, width: Int = LinearLayout.LayoutParams.MATCH_PARENT) { // TODO base class?
        try {
            val rootView = // TODO accept fragments and scrollView
                (context as Activity).findViewById<ContentFrameLayout>(android.R.id.content).getChildAt(0)
                        as LinearLayout
            layoutParams = LinearLayout.LayoutParams(
                if (width == LinearLayout.LayoutParams.MATCH_PARENT) width else convertDpToPixel(width),
                if (height == LinearLayout.LayoutParams.WRAP_CONTENT) height else convertDpToPixel(height)
            )
            this.layoutParams = layoutParams
            rootView.addView(this)
            this.requestLayout()
        } catch (e: Exception) {
            throw IllegalStateException("Your activity must have a LinearLayout as its root view for ATL Components to mount")
        }
    }

    class Style(
        var color: Int = 0
    )
}