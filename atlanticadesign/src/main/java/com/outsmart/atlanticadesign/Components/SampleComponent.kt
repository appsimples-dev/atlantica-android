package com.outsmart.atlanticadesign.Components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.outsmart.atlanticadesign.R

class SomeComponent @JvmOverloads constructor(context: Context,
                                            attributeSet: AttributeSet? = null,
                                            defStyle: Int = 0) : FrameLayout(context, attributeSet, defStyle) {

    init {
        View.inflate(context, R.layout.layout_sample_component, this)
    }

}