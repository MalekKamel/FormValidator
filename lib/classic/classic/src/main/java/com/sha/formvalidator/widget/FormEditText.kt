package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.sha.formvalidator.DefTextValidationHandler
import com.sha.formvalidator.TextValidationHandler
import com.sha.formvalidator.TextViewValidatable
import com.sha.formvalidator.Validatable

/**
 * An implementation of [Validatable] for [AppCompatEditText].
 */
open class FormEditText : AppCompatEditText, TextViewValidatable {
    override lateinit var validationHandler: TextValidationHandler
    override val string: String
        get() = text.toString()

    constructor(context: Context) : super(context) { setupDefaultValidator(null, context) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs, context) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs, context)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?, context: Context) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validationHandler = DefTextValidationHandler(this, context)
            return
        }
        validationHandler = DefTextValidationHandler(this, attrs, context)
    }

    override fun getBackground(): Drawable? {
        val background = super.getBackground()
        background?.clearColorFilter()
        return background
    }
}
