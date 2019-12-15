package com.sha.formvalidator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import io.reactivex.Single

open class Form: LinearLayout {

    open lateinit var formHelper: FormHelper

    constructor(context: Context) : super(context) { setup(null) }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) { setup(attrs) }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setup(attrs)
    }

    private fun setup(attrs: AttributeSet?) {
        formHelper = FormHelper()
    }

    open fun formFields() = formHelper.fields(this)

    open fun validate(): Boolean = FormValidator(formHelper.fields(this)).isValid

    open fun validateOnClick(view: View, validationCallback: (Boolean) -> Unit) {
        view.setOnClickListener { validationCallback(validate()) }
    }

    open fun single(): Single<Boolean> = RxFormValidator(formHelper.fields(this)).validate()

}