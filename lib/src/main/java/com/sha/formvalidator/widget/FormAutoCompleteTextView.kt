package com.sha.formvalidator.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.textview.DefTextViewValidator
import com.sha.formvalidator.textview.TextViewValidator

/**
 * AutoCompleteTextView Extension with validation.
 *
 */
open class FormAutoCompleteTextView : AppCompatAutoCompleteTextView, Validatable {

    lateinit var validator: TextViewValidator
    constructor(context: Context) : super(context) { setupDefaultValidator(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validator = DefTextViewValidator(this, context)
            return
        }
        validator = DefTextViewValidator(this, attrs, context)
    }


    /**
     * Add a validator to this AutoCompleteTextView. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: com.sha.formvalidator.textview.validator.Validator) {
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
