package com.outsmart.atlanticadesign.Atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.outsmart.atlanticadesign.R

class ATLSimpleLabel @JvmOverloads constructor(context: Context,
                                               attributeSet: AttributeSet? = null,
                                               defStyle: Int = 0) : TextView(context, attributeSet, defStyle) {

    init {
        var textColor = 0
        var fontSize = 0f
        var textAlignment = 0
        var backgroundColor = 0
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLSimpleLabel,
            0, 0).apply {

            try {
                textColor = getColor(R.styleable.ATLSimpleLabel_textColor, 0)
                fontSize = getDimension(R.styleable.ATLSimpleLabel_fontSize, 0f)
                textAlignment = getInteger(R.styleable.ATLSimpleLabel_textAlignment, 0)
                backgroundColor = getColor(R.styleable.ATLSimpleLabel_backgroundColor, 0)

            } finally {
                recycle()
            }
        }
        this.setTextColor(textColor)
        this.textSize = fontSize
        this.textAlignment = textAlignment
        this.setBackgroundColor(backgroundColor)
    }

}