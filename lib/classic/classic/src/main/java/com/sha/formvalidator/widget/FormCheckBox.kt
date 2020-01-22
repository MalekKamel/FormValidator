package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import com.sha.formvalidator.*
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.handler.CompoundButtonValidationHandler
import com.sha.formvalidator.handler.ValidationHandlerInterface

/**
 * An implementation of [Validatable] for [AppCompatCheckBox].
 */
open class FormCheckBox: AppCompatCheckBox, ValidatableWidget<Boolean> {
    override lateinit var validationHandler: ValidationHandlerInterface<Boolean>
    override val value: Boolean
        get() = isChecked

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        validationHandler = CompoundButtonValidationHandler(this, attrs)
    }
}


