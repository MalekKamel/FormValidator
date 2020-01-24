package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sha.formvalidator.handler.TextViewValidationHandler
import com.sha.formvalidator.handler.ValidationHandlerInterface
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.Validatable

/**
 * An implementation of [Validatable] for [AppCompatAutoCompleteTextView].
 */
open class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, ValidatableWidget<String> {
    override lateinit var validationHandler: ValidationHandlerInterface<String>
    override val value: String?
        get() = text.toString()

    constructor(context: Context) : super(context) { setupDefaultValidator(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?) {
        validationHandler = TextViewValidationHandler(this, attrs)
    }

    override fun getBackground(): Drawable? {
        val background = super.getBackground()
        background?.clearColorFilter()
        return background
    }
}
