package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatToggleButton
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.validation.OnOffValidation

open class FormToggleButton: AppCompatToggleButton, Validatable {
    private var validation: OnOffValidation = OnOffValidation.ON
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

        context.obtainStyledAttributes(attrs, R.styleable.FormToggleButton).run {
            val attr = getInt(R.styleable.FormToggleButton_toggleButtonValidation, OnOffValidation.ON.value)
            recycle()
            validation = OnOffValidation.fromValue(attr)
        }
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
        }
    }

}