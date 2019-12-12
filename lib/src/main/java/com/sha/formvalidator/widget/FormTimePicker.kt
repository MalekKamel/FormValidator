package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.TimePicker
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable

class FormTimePicker: TimePicker, Validatable {

    constructor(context: Context) : super(context) { setup(null, context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormTimePicker)

        typedArray.recycle()
    }

    override fun validate(): Boolean {
        return true
    }

}