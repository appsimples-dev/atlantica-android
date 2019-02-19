package com.outsmart.atlanticadesign.Atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.outsmart.atlanticadesign.R

class ATLIcon @JvmOverloads constructor(context: Context,
                                            attributeSet: AttributeSet? = null,
                                            defStyle: Int = 0) : ImageView(context, attributeSet, defStyle) {

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
        this.setImageResource(source)
    }

}