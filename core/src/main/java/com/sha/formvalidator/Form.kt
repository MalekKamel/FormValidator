package com.sha.formvalidator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.sha.formvalidator.model.CheckedValidation
import com.sha.formvalidator.model.FormOptions

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

        // the view is added programmatically
        if (attrs == null) return

        context.obtainStyledAttributes(attrs, R.styleable.Form).run {
            options.shakeOnError = getBoolean(R.styleable.Form_shakeOnError, true)
            options.ignoreHiddenFields = getBoolean(R.styleable.Form_ignoreHiddenFields, true)
            recycle()
        }
    }

    open fun validate(): Boolean = FormValidator(options, formHelper.fields(this, options)).isValid

    open fun validateOnClick(view: View, validationCallback: (Boolean) -> Unit) {
        view.setOnClickListener { validationCallback(validate()) }
    }

}