package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable
import java.lang.IllegalArgumentException

class FormCheckBox: AppCompatCheckBox, Validatable {
    private lateinit var validation: RequiredValidation
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null, context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormCheckBox)
        validation = RequiredValidation.fromValue(typedArray.getString(R.styleable.FormCheckBox_checkBoxValidation))
        typedArray.recycle()

        originalColor = currentTextColor
    }

    override fun validate(): Boolean {
        return when(validation) {
            RequiredValidation.REQUIRED -> {
                setTextColor(if(isChecked) originalColor else Color.RED)
                isChecked
            }

            RequiredValidation.NOT_REQUIRED -> {
                setTextColor(originalColor)
                true
            }

            RequiredValidation.NOT_DETECTABLE -> {
                throw IllegalArgumentException("Unknown validation!")
            }
        }
    }

}