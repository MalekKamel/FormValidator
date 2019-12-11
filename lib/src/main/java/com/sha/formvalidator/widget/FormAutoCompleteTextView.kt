package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

import androidx.appcompat.widget.AppCompatAutoCompleteTextView

import com.sha.formvalidator.Validatable
import com.sha.formvalidator.validation.DefaultEditTextValidator
import com.sha.formvalidator.validation.EditTextValidator

/**
 * AutoCompleteTextView Extension with validation.
 *
 */
class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, Validatable {

    lateinit var validator: EditTextValidator

    constructor(context: Context) : super(context) {
        setupDefaultValidator(null, context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setupDefaultValidator(attrs, context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs, context)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?, context: Context) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validator = DefaultEditTextValidator(this, context)
            return
        }

        validator = DefaultEditTextValidator(this, attrs, context)
    }


    /**
     * Add a validator to this AutoCompleteTextView. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: com.sha.formvalidator.validator.Validator) {
        this.validator.addValidator(validator)
    }

    /**
     * Calling *validate()* will cause the AutoCompleteTextView to go through
     * customValidators and call [com.sha.formvalidator.validator.Validator.isValid]
     *
     * @return true if the validity passes false otherwise.
     */
    override fun validate(): Boolean {
        return validator.validate()
    }


}
