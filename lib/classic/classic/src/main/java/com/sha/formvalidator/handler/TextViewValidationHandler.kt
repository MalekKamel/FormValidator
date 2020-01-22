package com.sha.formvalidator.handler

import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sha.formvalidator.AttrInfo

import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.factory.TextAttrValidatorFactory

/**
 * Default implementation of an [TextValidator]
 */
class TextViewValidationHandler(
        private var editText: EditText,
        attrs: AttributeSet?
) : ValidationHandlerInterface<String> {
    override lateinit var validator: AllValidator<String>
    override var attrValidatorFactory: AttrValidatorFactory<String> = TextAttrValidatorFactory
    override val value: String
        get() = editText.text.toString()

    init {
        val attrInfo = setup(attrs, editText.context)
        setupChangeListener(attrInfo)
    }

    private fun setupChangeListener(attrInfo: AttrInfo?) {
        editText.addTextChangedListener(object: TextWatcher {
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
        editText.error = e
    }

    private fun hasTextInputLayout(): Boolean {
        val parent = editText.parent.parent
        return parent is TextInputLayout
    }

    private fun textInputLayout(): TextInputLayout? {
        return editText.parent.parent as? TextInputLayout
    }
}
