package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.textview.DefTextViewValidationHandler
import com.sha.formvalidator.textview.TextViewValidationHandler
import com.sha.formvalidator.textview.validator.TextViewValidator

/**
 * AutoCompleteTextView Extension with validation.
 *
 */
open class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, Validatable {
    lateinit var validationHandler: TextViewValidationHandler
    constructor(context: Context) : super(context) { setupDefaultValidator(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validationHandler = DefTextViewValidationHandler(this, context)
            return
        }
        validationHandler = DefTextViewValidationHandler(this, attrs, context)
    }


    /**
     * Add a validator to this AutoCompleteTextView. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: TextViewValidator) {
        this.validationHandler.addValidator(validator)
    }

    /**
     * Calling *validate()* will cause the AutoCompleteTextView to go through
     * customValidators and call [com.sha.formvalidator.textview.validator.TextViewValidator.isValid]
     *
     * @return true if the validity passes false otherwise.
     */
    override fun validate(): Boolean {
        return validationHandler.validate()
    }


}
