package com.sha.formvalidator.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.textview.DefTextViewValidator
import com.sha.formvalidator.textview.TextViewValidator
import com.sha.formvalidator.textview.validator.Validator

/**
 * EditText Extension to be used in order to create forms in android.
 *
 */
open class FormEditText : AppCompatEditText, Validatable {
    lateinit var validator: TextViewValidator

    constructor(context: Context) : super(context) { setupDefaultValidator(null, context) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setupDefaultValidator(attrs, context) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setupDefaultValidator(attrs, context)
    }

    private fun setupDefaultValidator(attrs: AttributeSet?, context: Context) {
        if (attrs == null) {
            //support dynamic new FormEditText(context)
            validator = DefTextViewValidator(this, context)
            return
        }

        validator = DefTextViewValidator(this, attrs, context)
    }

    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator object
     */
    fun addValidator(validator: Validator) {
        this.validator.addValidator(validator)
    }

    /**
     * validate field
     *
     * @return true if valid.
     */
    override fun validate(): Boolean {
        return validator.validate()
    }

    override fun getBackground(): Drawable? {
        val background = super.getBackground()
        background?.clearColorFilter()
        return background
    }
}
