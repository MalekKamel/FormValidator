package com.sha.formvalidator.textview.validator.composite

import com.sha.formvalidator.textview.validator.TextViewValidator
import java.util.*

/**
 * Abstract class for a multivalidator.
 *
 * @see AndValidator
 *
 * @see OrValidator
 */
abstract class CompositeValidator(message: String, vararg validators: TextViewValidator) : TextViewValidator(message) {
    protected val validators: MutableList<TextViewValidator>

    init {
        this.validators = ArrayList(listOf(*validators))
    }

    fun enqueue(newValidator: TextViewValidator) {
        validators.add(newValidator)
    }


}
