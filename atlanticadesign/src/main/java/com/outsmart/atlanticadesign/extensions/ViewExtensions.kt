package com.outsmart.atlanticadesign.extensions

import android.view.View
import android.widget.TextView

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun TextView.alignTextLeft() {
    this.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
}

fun TextView.alignTextRight() {
    this.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
}

fun TextView.alignTextCenter() {
    this.textAlignment = View.TEXT_ALIGNMENT_CENTER
}