package com.outsmart.atlanticadesign.Molecules

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.outsmart.atlanticadesign.Helpers.Constants
import com.outsmart.atlanticadesign.Helpers.Resolver
import com.outsmart.atlanticadesign.R
import kotlinx.android.synthetic.main.molecule_image_logo.view.*

class ATLImageLogo @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var style = Style()

    init {
        inflateView()
        setStyleAttributes(attrs)
        applyStyle()
    }

    private fun inflateView() {
        View.inflate(context, R.layout.molecule_image_logo, this)
    }

    private fun setStyleAttributes(attributeSet: AttributeSet? = null) {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLImageLogo,
            0, 0
        ).apply {
            loadInitialStyle(this)
            loadInitialProps(this)
            recycle()
        }
    }

    private fun loadInitialProps(typedArray: TypedArray) {
        typedArray.apply {
            val resourceId = getResourceId(
                R.styleable.ATLImageLogo_imageLogo,
                Constants.RESOURCE_DEFAULT_VALUE
            )
            setImageLogo(resourceId)
        }
    }

    private fun setImageLogo(resourceId: Int?): ATLImageLogo {
        if (resourceId != null && resourceId >= 0) {
            imageLogoImageView.setImageResource(resourceId)
        }
        return this
    }

    private fun loadInitialStyle(typedArray: TypedArray) {
        val resolver = Resolver(context, typedArray)
        with(resolver) {
            style.apply {
                marginLeftSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingLarge,
                    R.styleable.ATLImageLogo_marginLeftSpacing
                )
                marginRightSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingLarge,
                    R.styleable.ATLImageLogo_marginRightSpacing
                )
                verticalSpacing = getResolvedDimenStyle(
                    R.attr.verticalSpacingSmall,
                    R.styleable.ATLImageLogo_verticalSpacing
                )
                moleculeHeight = getResolvedDimenStyle(
                    R.attr.sizeImageLogo,
                    R.styleable.ATLImageLogo_moleculeHeight
                )
            }
        }
    }

    fun setStyle(styleTransformer: (style: Style) -> Style): ATLImageLogo {
        this.style = styleTransformer.invoke(style)
        applyStyle()
        return this
    }

    private fun applyStyle() {
        setViewMargins()
    }

    private fun setViewMargins() {
        with(style) {
            setPadding(
                marginLeftSpacing,
                verticalSpacing,
                marginRightSpacing,
                verticalSpacing
            )
        }
    }

    inner class Style(
        var marginLeftSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var marginRightSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var verticalSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var moleculeHeight: Int = Constants.DIMENSION_DEFAULT_VALUE
    )
}