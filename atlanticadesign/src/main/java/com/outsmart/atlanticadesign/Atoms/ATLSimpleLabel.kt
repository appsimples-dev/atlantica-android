package com.outsmart.atlanticadesign.Atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.outsmart.atlanticadesign.R
import com.outsmart.atlanticadesign.enums.TextAlignment
import com.outsmart.atlanticadesign.extensions.alignTextCenter
import com.outsmart.atlanticadesign.extensions.alignTextLeft
import com.outsmart.atlanticadesign.extensions.alignTextRight

class ATLSimpleLabel @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : TextView(context, attributeSet, defStyle) {

    init {
        var textColor = 0
        var fontSize = 0f
        var textAlignment = 0
        var backgroundColor = 0
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLSimpleLabel,
            0, 0
        ).apply {

            try {
                textColor = getColor(R.styleable.ATLSimpleLabel_textColor, 0)
                fontSize = getDimension(R.styleable.ATLSimpleLabel_fontSize, -1f)
                textAlignment = getInteger(R.styleable.ATLSimpleLabel_textAlignment, -1)
                backgroundColor = getColor(R.styleable.ATLSimpleLabel_backgroundColor, -1)

            } finally {
                recycle()
            }
        }
        if (textColor != 0) this.setTextColor(textColor)
        if (fontSize >= 0) this.setTextSize(fontSize)
        if (backgroundColor >= 0) this.setBackgroundColor(backgroundColor)

        setTextAlignmentATL(textAlignment)
    }

    fun setTextAlignmentATL(textAlignment: Int) {
        if (textAlignment >= 0) {
            when (textAlignment) {
                TextAlignment.LEFT.ordinal -> alignTextLeft()
                TextAlignment.RIGHT.ordinal -> alignTextRight()
                TextAlignment.CENTER.ordinal -> alignTextCenter()
            }
        }
    }

    fun setTextAlignmentATL(textAlignment: TextAlignment) {
        when (textAlignment) {
            TextAlignment.LEFT -> alignTextLeft()
            TextAlignment.RIGHT -> alignTextRight()
            TextAlignment.CENTER -> alignTextCenter()
        }
    }
}