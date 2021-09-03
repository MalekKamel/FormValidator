package com.sha.formvalidator.compose

import com.sha.formvalidator.AbstractFormValidator
import com.sha.formvalidator.Validatable
import com.sha.formvalidator.model.FormOptions


/**
 * The class responsible for validating fields.
 */
class ComposeFormValidator<T : Validatable> : AbstractFormValidator<T> {

    /**
     * create an instance with list of fields to be validated.
     * @param fields the fields to be validated.
     */
    constructor(options: FormOptions = FormOptions.defaultOptions(), fields: List<T>) : super(
        options,
        fields
    )

    /**
     * create an instance with list of fields to be validated.
     * @param fields the fields to be validated.
     */
    constructor(fields: List<T>) : super(FormOptions.defaultOptions(), fields)

    /**
     * create an instance with var args of fields to be
     * validated.
     * @param fields the fields to be validated.
     */
    @SafeVarargs
    constructor(options: FormOptions = FormOptions.defaultOptions(), vararg fields: T) : super(
        options,
        *fields
    )

    /**
     * create an instance with var args of fields to be
     * validated.
     * @param fields the fields to be validated.
     */
    constructor(vararg fields: T) : super(FormOptions.defaultOptions(), *fields)


}
