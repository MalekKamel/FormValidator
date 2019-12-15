package com.sha.formvalidator.textview.validator.composite

import com.sha.formvalidator.textview.validator.TextViewValidator
import java.util.*

/**
 * Abstract class for a multi-validator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator(message: String, vararg validators: TextViewValidator) : TextViewValidator(message) {
    protected val validators: MutableList<TextViewValidator> = mutableListOf(*validators)

    fun enqueue(newValidator: TextViewValidator) {
        validators.add(newValidator)
    }
}
