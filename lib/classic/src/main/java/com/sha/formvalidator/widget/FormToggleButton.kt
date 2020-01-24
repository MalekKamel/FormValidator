package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatToggleButton
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.handler.CompoundButtonValidationHandler
import com.sha.formvalidator.handler.ValidationHandlerInterface

/**
 * An implementation of [Validatable] for [AppCompatToggleButton]
 */
open class FormToggleButton: AppCompatToggleButton, ValidatableWidget<Boolean> {
    override lateinit var validationHandler: ValidationHandlerInterface<Boolean>
    override val value: Boolean?
        get() = isChecked

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        validationHandler = CompoundButtonValidationHandler(this, attrs)
        // to initialize change listener. Nothing is needed here as the implementation is in the function
        setOnCheckedChangeListener(null)
    }

    override fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        // we set the listener here to avoid overriding our listener by the user
        super.setOnCheckedChangeListener { buttonView, isChecked ->
            validationHandler.onValueChanged(isChecked)
            listener?.onCheckedChanged(buttonView, isChecked)
        }
    }
}