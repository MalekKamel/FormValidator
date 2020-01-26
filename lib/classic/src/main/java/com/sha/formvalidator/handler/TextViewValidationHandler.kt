package com.sha.formvalidator.handler

import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.TextAttrValidatorFactory
import com.sha.formvalidator.model.AttrInfo

class TextViewValidationHandler(
        override val view: EditText,
        attrs: AttributeSet?
) : AbsValidationHandler<String>() {
    override var attrValidatorFactory: AttrValidatorFactory<String> = TextAttrValidatorFactory
    override val value: String
        get() = view.text.toString()

    init {
        val attrInfo = setup(attrs, view.context)
        setupChangeListener(attrInfo)
    }

    private fun setupChangeListener(attrInfo: AttrInfo?) {
        view.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                onValueChanged(s.toString())
                if (attrInfo?.validateOnChange == true) validate()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun showError(e: String?) {
        if (hasTextInputLayout()) {
            textInputLayout()?.error = e
            return
        }
        view.error = e
    }

    private fun hasTextInputLayout(): Boolean {
        val parent = view.parent.parent
        return parent is TextInputLayout
    }

    private fun textInputLayout(): TextInputLayout? {
        return view.parent.parent as? TextInputLayout
    }
}
