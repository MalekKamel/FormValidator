package com.sha.formvalidator.compose

import androidx.compose.State

class ComposeValidator<T: ValidatableModel> : AbstractComposeValidator<T> {

    /**
     * create an instance with list of models to be validated.
     * @param fields the fields to be validated.
     */
    constructor(models: CompositeValidation<T>) : super(models)

    /**
     * create an instance with list of models to be validated.
     * @param fields the fields to be validated.
     */
    constructor(fields: List<T>) : super(fields)

    /**
     * create an instance with list of models to be validated.
     * @param fields the fields to be validated.
     */
    constructor(vararg fields: T) : super(*fields)

}