package com.sha.formvalidator

import android.view.View
import com.sha.formvalidator.helper.AnimationHelper
import com.sha.formvalidator.model.FormOptions

/**
 * The base form validator that all validators must extend.
 */
abstract class AbstractFormValidator<T : Validatable> {
    private var options: FormOptions = FormOptions.defaultOptions()
    private var fields: List<T> = emptyList()
    val isValid: Boolean
        get() {
            if(fields.isEmpty()) return false
            var isValid = true
            fields.forEach {
                val fieldValid = it.validate()
                isValid = fieldValid && isValid
                if(options.shakeOnError && !fieldValid) AnimationHelper.error(it as? View)
            }
            return isValid
        }
    /**
     * create an instance with list of fields to be validated.
     */
    constructor(options: FormOptions, fields: List<T>) {
        this.fields = fields
        this.options = options
    }

    /**
     * create an instance with var args of fields to be
     * validated.
     */
    @SafeVarargs
    constructor(options: FormOptions, vararg fields: T) {
        this.fields = fields.asList()
        this.options = options
    }
}
