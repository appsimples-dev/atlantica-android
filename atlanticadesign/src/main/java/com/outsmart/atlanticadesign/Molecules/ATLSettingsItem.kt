package com.outsmart.atlanticadesign.Molecules

import android.app.Activity
import android.content.Context
import android.content.res.TypedArray
import android.support.v4.content.ContextCompat
import android.support.v7.widget.ContentFrameLayout
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.outsmart.atlanticadesign.Atoms.ATLIcon
import com.outsmart.atlanticadesign.Atoms.ATLSimpleLabel
import com.outsmart.atlanticadesign.Atoms.ATLSwitchButton
import com.outsmart.atlanticadesign.Helpers.convertDpToPixel
import com.outsmart.atlanticadesign.Helpers.resolveColorStyle
import com.outsmart.atlanticadesign.Helpers.resolveDimenStyle
import com.outsmart.atlanticadesign.Helpers.resolveEnumStyle
import com.outsmart.atlanticadesign.R
import kotlinx.android.synthetic.main.molecule_atl_settings_item.view.*
import java.lang.Exception
import java.lang.IllegalStateException

class ATLSettingsItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attributeSet, defStyle) {

    var id: String = ""
    private lateinit var style: Style

    lateinit var label: ATLSimpleLabel
    lateinit var icon: ATLIcon
    lateinit var iconContainer: LinearLayout
    lateinit var rightIcon: ATLIcon
    lateinit var switch: ATLSwitchButton

    init {
        View.inflate(getContext(), R.layout.molecule_atl_settings_item, this)

        initializeViews()

        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ATLSettingsItem,
            0, 0
        ).apply {
            loadInitialStyle(this)
            loadInitialProps(this)
            recycle()
        }

        applyStyle()
    }

    fun create(heightDp: Float): ATLSettingsItem {
        try {
            val rootView=
                (context as Activity).findViewById<ContentFrameLayout>(android.R.id.content).getChildAt(0)
                        as LinearLayout
            layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, convertDpToPixel(heightDp))
            this.layoutParams = layoutParams
            rootView.addView(this)
            this.requestLayout()
        } catch (e: Exception) {
            throw IllegalStateException("Your activity must have a LinearLayout as its root view for ATL Components to mount")
        }
        return this
    }

    private fun initializeViews() {
        label = atl_settings_item_label
        icon = atl_settings_item_icon
        iconContainer = atl_settings_item_icon_container
        switch = atl_settings_item_switch_button
        rightIcon = atl_settings_right_item_icon
    }

    private fun loadInitialStyle(input: TypedArray) {
        style = Style(
            resolveDimenStyle(context, input, R.attr.horizontalSpacingMargin, R.styleable.ATLSettingsItem_marginLeftSpacing, 0),
            resolveDimenStyle(context, input, R.attr.horizontalSpacingMargin, R.styleable.ATLSettingsItem_marginRightSpacing, 0),
            resolveDimenStyle(context, input, R.attr.verticalSpacingSmall, R.styleable.ATLSettingsItem_marginTopSpacing, 0),
            resolveDimenStyle(context, input, R.attr.verticalSpacingSmall, R.styleable.ATLSettingsItem_marginBottomSpacing, 0),
            resolveDimenStyle(context, input, R.attr.iconListSize, R.styleable.ATLSettingsItem_iconHeight, 0),
            resolveDimenStyle(context, input, R.attr.iconListSize, R.styleable.ATLSettingsItem_iconWidth, 0),
            resolveDimenStyle(context, input, R.attr.horizontalSpacingMedium, R.styleable.ATLSettingsItem_iconLabelSpacing, 0),
            resolveDimenStyle(context, input, R.attr.horizontalSpacingSmall, R.styleable.ATLSettingsItem_labelSwitchSpacing, 0),
            resolveDimenStyle(context, input, R.attr.iconListSize, R.styleable.ATLSettingsItem_rightIconHeight, 0),
            resolveDimenStyle(context, input, R.attr.iconListSize, R.styleable.ATLSettingsItem_rightIconWidth, 0),
            null,
            resolveColorStyle(context, input, R.attr.mediumGrayColor, R.styleable.ATLSettingsItem_labelFontColor, 0),
            resolveEnumStyle(context, input, R.attr.textAlignment, R.styleable.ATLSettingsItem_textAlignment, 0)
        )
    }

    private fun loadInitialProps(input: TypedArray) {
        input.apply {
            setText(getString(R.styleable.ATLSettingsItem_text) ?: "")
            setIcon(getResourceId(R.styleable.ATLSettingsItem_icon, -1))
            setRightComponentVariant(getInteger(R.styleable.ATLSettingsItem_rightComponentVariant, -1))
            setRightIcon(getResourceId(R.styleable.ATLSettingsItem_rightIcon, -1))
        }
    }

    private fun applyStyle() {
        setPadding(style.marginLeftSpacing, style.marginTopSpacing, style.marginRightSpacing, style.marginBottomSpacing)
        setBackgroundColor(ContextCompat.getColor(context, R.color.ahahaha))
        icon.layoutParams.width = style.iconWidth
        icon.layoutParams.height = style.iconHeight
        rightIcon.layoutParams.width = style.rightIconWidth
        rightIcon.layoutParams.height = style.rightIconHeight
        (iconContainer.layoutParams as RelativeLayout.LayoutParams).rightMargin = style.iconLabelSpacing

        label.setTextColor(style.labelFontColor)
        label.setTextAlignmentATL(style.textAlignment)
    }


    /**
     * Prop setting methods
     */

    fun setText(text: String): ATLSettingsItem {
        label.text = text
        return this
    }

    fun setText(resId: Int): ATLSettingsItem {
        label.setText(resId)
        return this
    }

    fun setTextAlignment(textAlignment: ATLSimpleLabel.TextAlignment): ATLSettingsItem {
        label.setTextAlignmentATL(textAlignment)
        return this
    }

    fun setIcon(resId: Int?): ATLSettingsItem {
        if (resId != null && resId >= 0) {
            icon.setImageResource(resId)
            iconContainer.visibility = View.VISIBLE
        } else {
            iconContainer.visibility = View.GONE
        }
        return this
    }

    fun setRightIcon(resId: Int?): ATLSettingsItem {
        if (resId != null && resId >= 0) {
            rightIcon.setImageResource(resId)
            rightIcon.visibility = View.VISIBLE
        } else {
            rightIcon.visibility = View.GONE
        }
        return this
    }

    private fun setRightComponentVariant(variant: Int): ATLSettingsItem {
        if (variant < 0) return this
        when(variant) {
            RightComponentVariant.NONE.ordinal -> setVariantNone()
            RightComponentVariant.ICON.ordinal -> setVariantIcon()
            RightComponentVariant.SWITCH.ordinal -> setVariantSwitch()
        }
        return this
    }

    fun setRightComponentVariant(variant: RightComponentVariant): ATLSettingsItem {
        when(variant) {
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
        switch.visibility = View.GONE
        rightIcon.visibility = View.GONE
    }

    private fun setVariantIcon() {
        switch.visibility = View.GONE
        rightIcon.visibility = View.VISIBLE
    }

    private fun setVariantSwitch() {
        switch.visibility = View.VISIBLE
        rightIcon.visibility = View.GONE
    }

    /**
     * Style Class for Settings Item
     */
    inner class Style(
        var marginLeftSpacing: Int,
        var marginRightSpacing: Int,
        var marginTopSpacing: Int,
        var marginBottomSpacing: Int,
        var iconHeight: Int,
        var iconWidth: Int,
        var iconLabelSpacing: Int,
        var labelSwitchSpacing: Int,
        var rightIconHeight: Int,
        var rightIconWidth: Int,
        var labelFont: Any?,
        var labelFontColor: Int,
        var textAlignment: Int
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