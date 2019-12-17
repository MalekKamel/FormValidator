package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.model.CheckedValidation

/**
 * An implementation of [Validatable] for [AppCompatCheckBox].
 */
open class FormCheckBox: AppCompatCheckBox, Validatable {
    private var validation: CheckedValidation = CheckedValidation.CHECKED
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        originalColor = currentTextColor

        // the view is added programmatically
        if (attrs == null) return

        context.obtainStyledAttributes(attrs, R.styleable.FormCheckBox).run {
            val attr = getInt(R.styleable.FormCheckBox_checkBoxValidation, CheckedValidation.CHECKED.value)
            recycle()
            validation = CheckedValidation.fromValue(attr)
        }
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
        }
    }

}