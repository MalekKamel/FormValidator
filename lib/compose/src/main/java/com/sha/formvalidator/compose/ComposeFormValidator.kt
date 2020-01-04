package com.sha.formvalidator.compose

import androidx.compose.State

class ComposeFormValidator<T : ValidatableTextModel> : AbstractComposeFormValidator<T> {

    /**
     * create an instance with list of models to be validated.
     * @param fields the fields to be validated.
     */
    constructor(fields: List<State<T>>) : super(fields)

    /**
     * create an instance with list of models to be validated.
     * @param fields the fields to be validated.
     */
    constructor(vararg fields: State<T>) : super(*fields)

}