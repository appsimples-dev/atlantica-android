package com.outsmart.atlanticadesign.Atoms

import android.content.Context
import android.util.AttributeSet
import android.view.View
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
            when(textAlignment) {
                0 -> this.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                1 -> this.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
                2 -> this.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
        }
    }

    fun setTextAlignmentATL(textAlignment: TextAlignment) {
        when(textAlignment) {
            TextAlignment.LEFT -> this.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
            TextAlignment.RIGHT -> this.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
            TextAlignment.CENTER -> this.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }

    enum class TextAlignment {
        LEFT, RIGHT, CENTER // It is important that they are in the same order as the its attr definition
    }
}