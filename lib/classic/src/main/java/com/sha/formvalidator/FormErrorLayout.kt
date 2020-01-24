package com.sha.formvalidator

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * A [LinearLayout] that wraps all [Validatable] fields and applies
 * different validators on each field.
 */
open class FormErrorLayout: LinearLayout {
    private val tvError: TextView by lazy {
            val tv = TextView(context)
            tv.setTextColor(Color.RED)

            val params = LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.marginStart = 30
            params.marginEnd = 30

            addView(tv, params)
         tv
        }

    constructor(context: Context) : super(context) { setup() }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup() }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup()
    }

    private fun setup() {
        orientation = VERTICAL
    }

    fun setError(error: String?) {
        val tv = tvError
        tv.text = error
        tv.visibility = if (error.isNullOrEmpty()) View.GONE else View.VISIBLE
    }
}