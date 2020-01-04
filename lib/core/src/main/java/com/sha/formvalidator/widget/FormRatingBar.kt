package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.core.content.ContextCompat
import com.sha.formvalidator.R
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.model.RequiredValidation

/**
 * An implementation of [Validatable] for [AppCompatRatingBar].
 */
open class FormRatingBar: AppCompatRatingBar, Validatable {
    private var validation: RequiredValidation = RequiredValidation.REQUIRED
    private var originalColor: Int = -1

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        originalColor = (background as? ColorDrawable)?.color ?: Color.TRANSPARENT

        // the view is added programmatically
        if (attrs == null) return

        context.obtainStyledAttributes(attrs, R.styleable.FormRatingBar).run {
            val attr = getInt(R.styleable.FormRatingBar_ratingBarValidation,
                    RequiredValidation.REQUIRED.value)
            recycle()
            validation = RequiredValidation.fromValue(attr)
        }
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

            RequiredValidation.NOT_REQUIRED -> { true }
        }
    }
}