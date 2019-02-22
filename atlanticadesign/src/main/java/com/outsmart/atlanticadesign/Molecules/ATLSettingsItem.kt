package com.outsmart.atlanticadesign.Molecules

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.outsmart.atlanticadesign.R

class ATLSettingsItem @JvmOverloads constructor(context: Context,
                                                attributeSet: AttributeSet? = null,
                                                defStyle: Int = 0) : LinearLayout(context, attributeSet, defStyle) {

    init {
        var source = 0
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLIcon,
            0, 0).apply {

            try {
                source = getResourceId(R.styleable.ATLIcon_source, 0)
            } finally {
                recycle()
            }
        }
    }

}