package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.SwitchCompat
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable

open class FormSwitch: SwitchCompat, Validatable {
    private lateinit var validation: OnOffValidation
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null, context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormSwitch)
        validation = OnOffValidation.fromValue(typedArray.getInt(R.styleable.FormSwitch_switchValidation, OnOffValidation.UNDEFINED.value))
        typedArray.recycle()
        originalColor = currentTextColor
    }

    override fun validate(): Boolean {
        return when(validation) {
            OnOffValidation.ON -> {
                setTextColor(if(isChecked) originalColor else Color.RED)
                isChecked
            }

            OnOffValidation.OFF -> {
                setTextColor(if(isChecked) Color.RED  else originalColor)
                !isChecked
            }

            OnOffValidation.UNDEFINED -> {
                setTextColor(originalColor)
                true
            }
        }
    }

}