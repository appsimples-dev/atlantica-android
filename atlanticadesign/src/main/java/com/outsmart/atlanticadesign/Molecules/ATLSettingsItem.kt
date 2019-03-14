package com.outsmart.atlanticadesign.Molecules

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.ContentFrameLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.outsmart.atlanticadesign.Atoms.ATLIcon
import com.outsmart.atlanticadesign.Atoms.ATLSimpleLabel
import com.outsmart.atlanticadesign.Atoms.ATLSwitchButton
import com.outsmart.atlanticadesign.Helpers.*
import com.outsmart.atlanticadesign.R
import com.outsmart.atlanticadesign.enums.TextAlignment
import com.outsmart.atlanticadesign.extensions.hide
import com.outsmart.atlanticadesign.extensions.show
import kotlinx.android.synthetic.main.molecule_atl_settings_item.view.*
import java.lang.Exception
import java.lang.IllegalStateException

class ATLSettingsItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    var id: String = ""
    private var style: Style = Style()

    /* Views and Components */
    private val label: ATLSimpleLabel by lazy { atl_settings_item_label }
    private val icon: ATLIcon by lazy { atl_settings_item_icon }
    private val iconContainer: LinearLayout by lazy { atl_settings_item_icon_container }
    private val rightIcon: ATLIcon by lazy { atl_settings_right_item_icon }
    private val switch: ATLSwitchButton by lazy { atl_settings_item_switch_button }

    /*  */
    var styleTransformer: ((style: Style) -> Style)? = null

    init {
        inflateView()
        setStyleAttributes(attributeSet)
        applyStyle()
    }

    private fun setStyleAttributes(attributeSet: AttributeSet? = null) {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLSettingsItem,
            0, 0
        ).apply {
            loadInitialStyle(this)
            loadInitialProps(this)
            recycle()
        }
    }

    private fun inflateView() {
        View.inflate(context, R.layout.molecule_atl_settings_item, this)
    }

    fun create(
        height: Int = LayoutParams.WRAP_CONTENT,
        width: Int = LayoutParams.MATCH_PARENT
    ): ATLSettingsItem { // TODO base class?
        try {
            addViewToLinearLayout(height, width)
        } catch (e: Exception) {
            throw IllegalStateException("Your activity must have a LinearLayout as its root view for ATL Components to mount")
        }
        return this
    }

    private fun addViewToLinearLayout(height: Int, width: Int) {
        val rootView = // TODO accept fragments and scrollView
            (context as Activity).findViewById<ContentFrameLayout>(android.R.id.content).getChildAt(0)
                    as LinearLayout
        layoutParams = LinearLayout.LayoutParams(
            if (width == LayoutParams.MATCH_PARENT) width else convertDpToPixel(width),
            if (height == LayoutParams.WRAP_CONTENT) height else convertDpToPixel(height)
        )
        this.layoutParams = layoutParams
        rootView.addView(this)
        this.requestLayout()
    }

    private fun loadInitialStyle(input: TypedArray) {
        with(Resolver(context, input)) {
            style.apply {
                marginLeftSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingMargin,
                    R.styleable.ATLSettingsItem_marginLeftSpacing
                )
                marginRightSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingMargin,
                    R.styleable.ATLSettingsItem_marginRightSpacing
                )
                marginTopSpacing = getResolvedDimenStyle(
                    R.attr.verticalSpacingSmall,
                    R.styleable.ATLSettingsItem_marginTopSpacing
                )
                marginBottomSpacing = getResolvedDimenStyle(
                    R.attr.verticalSpacingSmall,
                    R.styleable.ATLSettingsItem_marginBottomSpacing
                )
                iconHeight = getResolvedDimenStyle(
                    R.attr.iconListSize,
                    R.styleable.ATLSettingsItem_iconHeight
                )
                iconWidth = getResolvedDimenStyle(
                    R.attr.iconListSize,
                    R.styleable.ATLSettingsItem_iconWidth
                )
                iconLabelSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingMedium,
                    R.styleable.ATLSettingsItem_iconLabelSpacing
                )
                labelSwitchSpacing = getResolvedDimenStyle(
                    R.attr.horizontalSpacingSmall,
                    R.styleable.ATLSettingsItem_labelSwitchSpacing
                )
                rightIconHeight = getResolvedDimenStyle(
                    R.attr.iconListSize,
                    R.styleable.ATLSettingsItem_rightIconHeight
                )
                rightIconWidth = getResolvedDimenStyle(
                    R.attr.iconListSize,
                    R.styleable.ATLSettingsItem_rightIconWidth
                )
                labelFontColor = getResolvedColorStyle(
                    R.attr.mediumGrayColor,
                    R.styleable.ATLSettingsItem_labelFontColor,
                    0
                )
                textAlignment = TextAlignment.values()[getResolvedEnumStyle(
                    R.attr.textAlignment,
                    R.styleable.ATLSettingsItem_textAlignment,
                    0
                )]
            }
        }
        // Apply initial style transformer if existent
        style = this.styleTransformer?.invoke(style) ?: style
        // TODO make extensible?
    }

    private fun loadInitialProps(input: TypedArray) {
        input.apply {
            setText(getString(R.styleable.ATLSettingsItem_text) ?: "")
            setIcon(
                getResourceId(
                    R.styleable.ATLSettingsItem_icon,
                    Constants.RESOURCE_DEFAULT_VALUE
                )
            )
            setRightComponentVariant(
                getInteger(
                    R.styleable.ATLSettingsItem_rightComponentVariant,
                    Constants.RESOURCE_DEFAULT_VALUE
                )
            )
            setRightIcon(getResourceId(R.styleable.ATLSettingsItem_rightIcon, Constants.RESOURCE_DEFAULT_VALUE))
        }
    }

    private fun applyStyle() {
        setViewPadding()
        setViewParameters()
        setLabelStyle()
    }

    private fun setLabelStyle() {
        with(style) {
            label.setTextColor(labelFontColor)
            label.setTextAlignmentATL(textAlignment)
        }
    }

    private fun setViewParameters() {
        with(style) {
            icon.layoutParams.width = iconWidth
            icon.layoutParams.height = iconHeight
            rightIcon.layoutParams.width = rightIconWidth
            rightIcon.layoutParams.height = rightIconHeight
            (iconContainer.layoutParams as RelativeLayout.LayoutParams).rightMargin = iconLabelSpacing
        }
    }

    private fun setViewPadding() {
        with(style) {
            setPadding(
                marginLeftSpacing,
                marginTopSpacing,
                marginRightSpacing,
                marginBottomSpacing
            )
        }
    }


    /**
     * Prop setting methods
     */
    fun setStyle(styleTransformer: (style: Style) -> Style): ATLSettingsItem {
        this.style = styleTransformer.invoke(style)
        this.applyStyle()
        return this
    }

    fun setText(text: String): ATLSettingsItem {
        label.text = text
        return this
    }

    fun setText(resId: Int): ATLSettingsItem {
        label.setText(resId)
        return this
    }

    fun setIcon(resId: Int?): ATLSettingsItem {
        if (resId != null && resId >= 0) {
            icon.setImageResource(resId)
            iconContainer.show()
        } else {
            iconContainer.hide()
        }
        return this
    }

    fun setRightIcon(resId: Int?): ATLSettingsItem {
        if (resId != null && resId >= 0) {
            rightIcon.setImageResource(resId)
            rightIcon.show()
        } else {
            rightIcon.hide()
        }
        return this
    }

    private fun setRightComponentVariant(variant: Int): ATLSettingsItem {
        if (variant < 0) return this
        when (variant) {
            RightComponentVariant.NONE.ordinal -> setVariantNone()
            RightComponentVariant.ICON.ordinal -> setVariantIcon()
            RightComponentVariant.SWITCH.ordinal -> setVariantSwitch()
        }
        return this
    }

    fun setRightComponentVariant(variant: RightComponentVariant): ATLSettingsItem {
        when (variant) {
            RightComponentVariant.NONE -> setVariantNone()
            RightComponentVariant.ICON -> setVariantIcon()
            RightComponentVariant.SWITCH -> setVariantSwitch()
        }
        return this
    }

    fun setOnToggle(callback: (checked: Boolean, id: String) -> Unit): ATLSettingsItem {
        switch.setOnCheckedChangeListener { _, isChecked -> callback(isChecked, id) }
        return this
    }

    fun setOnSelectItem(callback: (id: String) -> Unit): ATLSettingsItem {
        this.setOnClickListener { callback(id) }
        return this
    }

    private fun setVariantNone() {
        switch.hide()
        rightIcon.hide()
    }

    private fun setVariantIcon() {
        switch.hide()
        rightIcon.show()
    }

    private fun setVariantSwitch() {
        switch.hide()
        rightIcon.show()
    }

    /**
     * Style Class for Settings Item
     */
    inner class Style(
        var marginLeftSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var marginRightSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var marginTopSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var marginBottomSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var iconHeight: Int = Constants.MARGIN_DEFAULT_VALUE,
        var iconWidth: Int = Constants.MARGIN_DEFAULT_VALUE,
        var iconLabelSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var labelSwitchSpacing: Int = Constants.MARGIN_DEFAULT_VALUE,
        var rightIconHeight: Int = Constants.DIMENSION_DEFAULT_VALUE,
        var rightIconWidth: Int = Constants.DIMENSION_DEFAULT_VALUE,
        var labelFont: Any? = null,
        var labelFontColor: Int = 0,
        var textAlignment: TextAlignment = TextAlignment.LEFT
    )

    /**
     * Right Component Variant
     */
    enum class RightComponentVariant {
        NONE,
        ICON,
        SWITCH
    }
}