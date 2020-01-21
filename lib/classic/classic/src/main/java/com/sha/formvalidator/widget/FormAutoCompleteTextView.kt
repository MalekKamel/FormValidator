package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sha.formvalidator.DefTextValidationHandler
import com.sha.formvalidator.TextValidationHandler
import com.sha.formvalidator.TextViewValidatable
import com.sha.formvalidator.Validatable

/**
 * An implementation of [Validatable] for [AppCompatAutoCompleteTextView].
 */
open class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, TextViewValidatable {
    override lateinit var validationHandler: TextValidationHandler
    override val string: String
        get() = text.toString()

    constructor(context: Context) : super(context) { setupDefaultValidator(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validationHandler = DefTextValidationHandler(this, context)
            return
        }
        validationHandler = DefTextValidationHandler(this, attrs, context)
    }
}
