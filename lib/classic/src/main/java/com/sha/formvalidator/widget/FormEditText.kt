package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.sha.formvalidator.handler.TextViewValidationHandler
import com.sha.formvalidator.handler.ValidationHandlerInterface
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.Validatable

/**
 * An implementation of [Validatable] for [AppCompatEditText].
 */
open class FormEditText : AppCompatEditText, ValidatableWidget<String> {
    override lateinit var validationHandler: ValidationHandlerInterface<String>
    override val value: String?
        get() = text.toString()

    constructor(context: Context) : super(context) { setup(null, context) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs, context) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs, context)
    }

    private fun setup(attrs: AttributeSet?, context: Context) {
        validationHandler = TextViewValidationHandler(this, attrs)
    }

    override fun getBackground(): Drawable? {
        val background = super.getBackground()
        background?.clearColorFilter()
        return background
    }
}
