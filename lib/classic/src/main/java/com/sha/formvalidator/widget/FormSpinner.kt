package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.ValidatableWidget
import com.sha.formvalidator.handler.SpinnerValidationHandler
import com.sha.formvalidator.handler.ValidationHandler


/**
 * Custom spinner which is valid only if the first item isn't selected.
 * To create a custom Form filed, just implement [Validatable] interface
 * and put your validation logic inside validate() function
 */
class FormSpinner: AppCompatSpinner, ValidatableWidget<String> {
    override lateinit var validationHandler: ValidationHandler<String>
    override val value: String?
        get() = selectedItem?.toString()

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        validationHandler = SpinnerValidationHandler(this, attrs)
        // to initialize change listener. Nothing is needed here as the implementation is in the function
        onItemSelectedListener = null
    }

    override fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        // we set the listener here to avoid overriding our listener by the user
        super.setOnItemSelectedListener(
                object : OnItemSelectedListener {
                    override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View, position: Int, id: Long) {
                        validationHandler.onValueChanged(selectedItem?.toString() ?: "")
                        listener?.onItemSelected(parentView, selectedItemView, position, id)
                    }
                    override fun onNothingSelected(parentView: AdapterView<*>?) {
                        listener?.onNothingSelected(parentView)
                    }
                }
        )
    }
}