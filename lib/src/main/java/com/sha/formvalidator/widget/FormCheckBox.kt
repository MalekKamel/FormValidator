package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable

open class FormCheckBox: AppCompatCheckBox, Validatable {
    private lateinit var validation: CheckedValidation
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null, context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormCheckBox)
        validation = CheckedValidation.fromValue(typedArray.getInt(R.styleable.FormCheckBox_checkBoxValidation, CheckedValidation.UNDEFINED.value))
        typedArray.recycle()
        originalColor = currentTextColor
    }

    override fun validate(): Boolean {
        return when(validation) {
            CheckedValidation.CHECKED -> {
                setTextColor(if(isChecked) originalColor else Color.RED)
                isChecked
            }

            CheckedValidation.UNCHECKED -> {
                setTextColor(if(isChecked) Color.RED  else originalColor)
                !isChecked
            }

            CheckedValidation.UNDEFINED -> {
                setTextColor(originalColor)
                true
            }
        }
    }

}