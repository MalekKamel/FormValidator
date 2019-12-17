package com.sha.formvalidator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.sha.formvalidator.model.FormOptions
import io.reactivex.Single

/**
 * A [LinearLayout] that wraps all [Validatable] fields and applies
 * different validators on each field.
 */
open class Form: LinearLayout {

    open lateinit var formHelper: FormHelper

    var options: FormOptions = FormOptions.defaultOptions()

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        formHelper = FormHelper()
    }

    open fun validate(): Boolean = FormValidator(options, formHelper.fields(this, options)).isValid

    open fun validateOnClick(view: View, validationCallback: (Boolean) -> Unit) {
        view.setOnClickListener { validationCallback(validate()) }
    }

    open fun single(): Single<Boolean> = RxFormValidator(formHelper.fields(this, options)).validate()

}