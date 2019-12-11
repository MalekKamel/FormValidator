package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet

import com.sha.formvalidator.Validatable
import com.sha.formvalidator.validation.DefaultEditTextValidator
import com.sha.formvalidator.validation.EditTextValidator
import com.sha.formvalidator.validator.Validator

import androidx.appcompat.widget.AppCompatEditText

/**
 * EditText Extension to be used in order to create forms in android.
 *
 */
class FormEditText : AppCompatEditText, Validatable {
    var validator: EditTextValidator? = null

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
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: Validator) {
        this.validator!!.addValidator(validator)
    }

    /**
     * validate field
     *
     * @return true if valid.
     */
    override fun validate(): Boolean {
        return validator!!.validate()
    }

    override fun getBackground(): Drawable? {
        val background = super.getBackground()
        background?.clearColorFilter()
        return background
    }
}
