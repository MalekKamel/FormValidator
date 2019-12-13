package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.core.content.ContextCompat
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable

open class FormRatingBar: AppCompatRatingBar, Validatable {
    private lateinit var validation: RequiredValidation
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null, context) }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormRatingBar)
        validation = RequiredValidation.fromValue(typedArray.getInt(R.styleable.FormRatingBar_ratingBarValidation, RequiredValidation.UNDEFINED.value))
        typedArray.recycle()
        originalColor = (background as? ColorDrawable)?.color ?: Color.TRANSPARENT
    }

    private fun isValid(): Boolean = rating > 0
    
    private fun validationColor(isValid: Boolean): Int {
       return if(isValid) originalColor else ContextCompat.getColor(context, R.color.red_light)
    }

    override fun validate(): Boolean {
        return when(validation) {
            RequiredValidation.REQUIRED -> {
                val isValid = isValid()
                setBackgroundColor(validationColor(isValid))
                isValid
            }

            RequiredValidation.NOT_REQUIRED -> {
                setBackgroundColor(validationColor(true))
                true
            }

            RequiredValidation.UNDEFINED -> {
                true
            }
        }
    }
}