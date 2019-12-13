package com.sha.formvalidator

import android.view.View

class FormValidator<T : Validatable> : BaseFormValidator<T> {
    val isValid: Boolean
        get() = isValidForm

    constructor(fields: List<T>) : super(fields)

    @SafeVarargs
    constructor(vararg fields: T) : super(*fields)

    fun validateOnClick(view: View, listener: (Boolean) -> Unit) {
        view.setOnClickListener { listener(isValidForm) }
    }
}
