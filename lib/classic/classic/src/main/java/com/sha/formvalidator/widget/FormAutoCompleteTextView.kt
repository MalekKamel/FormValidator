package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.model.CompositeValidatorInfo
import com.sha.formvalidator.DefTextValidationHandler
import com.sha.formvalidator.TextValidationHandler
import com.sha.formvalidator.core.validator.TextValidator

/**
 * An implementation of [Validatable] for [AppCompatAutoCompleteTextView].
 */
open class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, Validatable {
    lateinit var validationHandler: TextValidationHandler
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

    /**
     * Add a validator to this AutoCompleteTextView. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: TextValidator) {
        this.validationHandler.addValidator(validator)
    }

    fun addValidators(block: CompositeValidatorInfo<String>.() -> Unit) {
        CompositeValidatorInfo<String>().apply { block() }
                .validators
                .map { validationHandler.addValidator(it) }
    }

    /**
     * Calling *validate()* will cause the AutoCompleteTextView to go through
     * customValidators and call [com.sha.formvalidator.core.textview.validator.TextValidator.isValid]
     *
     * @return true if the validity passes false otherwise.
     */
    override fun validate(): Boolean {
        return validationHandler.validate()
    }


}
