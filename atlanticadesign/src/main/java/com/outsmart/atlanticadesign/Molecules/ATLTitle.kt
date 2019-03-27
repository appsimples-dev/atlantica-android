package com.outsmart.atlanticadesign.Molecules

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import com.outsmart.atlanticadesign.Helpers.Constants
import com.outsmart.atlanticadesign.Helpers.Resolver
import com.outsmart.atlanticadesign.Helpers.convertDpToPixel
import com.outsmart.atlanticadesign.R
import com.outsmart.atlanticadesign.enums.FontWeight
import com.outsmart.atlanticadesign.enums.TextAlignment
import com.outsmart.atlanticadesign.enums.TextCase
import kotlinx.android.synthetic.main.molecule_title.view.*

class ATLTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var style = Style()

    init {
        inflateView()
        setStyleAttributes(attrs)
        applyStyle()
    }

    private fun inflateView() {
        View.inflate(context, R.layout.molecule_title, this)
    }

    private fun setStyleAttributes(attributeSet: AttributeSet? = null) {
        context.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLTitle,
            0, 0
        ).apply {
            loadInitialStyle(this)
            loadInitialState(this)
            recycle()
        }
    }

    private fun loadInitialStyle(typedArray: TypedArray) {
        val resolver = Resolver(context, typedArray)
        with(resolver) {
            style.apply {
                labelFontColor = getResolvedColorStyle(
                    R.attr.colorPrimary,
                    R.styleable.ATLTitle_labelFontColor
                )
                labelFontSize = getResolvedDimenStyle(
                    R.attr.fontSizeHighlight,
                    R.styleable.ATLTitle_labelFontSize,
                    Constants.FONT_SIZE_DEFAULT
                )
                labelFontWeight = FontWeight.values()[getResolvedEnumStyle(
                    R.attr.fontWeightStrong,
                    R.styleable.ATLTitle_labelFontWeight
                )]
                labelTextAlignment = TextAlignment.values()[getResolvedEnumStyle(
                    R.attr.textAlignmentTitle,
                    R.styleable.ATLTitle_labelTextAlignment
                )]
                labelTextCase = TextCase.values()[getResolvedEnumStyle(
                    R.attr.textCaseHighlight,
                    R.styleable.ATLTitle_labelTextCase
                )]
                marginRightSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingMargin,
                    R.styleable.ATLTitle_marginRightSpacing
                )
                marginLeftSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingMargin,
                    R.styleable.ATLTitle_marginLeftSpacing
                )
            }
        }
    }

    private fun loadInitialState(typedArray: TypedArray) {
        typedArray.apply {
            val inputText = getString(R.styleable.ATLTitle_text) ?: ""
            setText(inputText)
        }
    }

    private fun applyStyle() {
        setViewPadding()
        setLabelStyle()
    }

    private fun setViewPadding() {
        with(style) {
            setPadding(
                marginLeftSpacing,
                verticalSpacing,
                marginRightSpacing,
                verticalSpacing
            )
        }
    }

    private fun setLabelStyle() {
        setLabelTextSize()
        setLabelFontColor()
        setLabelFontWeight()
        molTitleSimpleLabel.setTextAlignmentATL(style.labelTextAlignment)
    }

    private fun setLabelFontWeight() {
        with(molTitleSimpleLabel) {
            when (style.labelFontWeight) {
                FontWeight.Bold -> setTypeface(typeface, Typeface.BOLD)
                else -> setTypeface(typeface, Typeface.NORMAL)
            }
        }
    }

    private fun setLabelTextSize() {
        molTitleSimpleLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, style.labelFontSize.toFloat())
    }

    private fun setLabelFontColor() {
        molTitleSimpleLabel.setTextColor(style.labelFontColor)
    }

    private fun setText(labelText: String) {
        molTitleSimpleLabel.text = labelText
    }

    inner class Style(
        var labelFontFamily: Int = Constants.RESOURCE_VALUE_UNAVAILABLE,
        var labelFontColor: Int = Constants.RESOURCE_VALUE_UNAVAILABLE,
        var labelFontSize: Int = Constants.FONT_SIZE_DEFAULT,
        var labelFontWeight: FontWeight = FontWeight.Regular,
        var labelTextAlignment: TextAlignment = TextAlignment.LEFT,
        var labelTextCase: TextCase = TextCase.UpperLow,
        var marginRightSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var marginLeftSpacing: Int = Constants.MARGIN_DEFAULT_VALUE
    ) {
        val verticalSpacing: Int = convertDpToPixel(Constants.SPACING_DEFAULT)
    }
}