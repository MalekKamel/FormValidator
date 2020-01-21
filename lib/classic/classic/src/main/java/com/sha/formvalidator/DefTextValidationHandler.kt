package com.sha.formvalidator

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.TextValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.CompositeValidator

/**
 * Default implementation of an [TextValidator]
 */
class DefTextValidationHandler : TextValidationHandler {
    /**
     * The custom validators set using
     */
    private lateinit var mValidator: CompositeValidator<String>
    private lateinit var editText: EditText

    private val attrInfo = TextViewAttrInfo()

    /**
     * support dynamic new DefaultEditTextValidator() ,used for Java call
     *
     * @param editText EditText
     * @param context Context
     */
    constructor(editText: EditText, context: Context) {
        setupDynamically(editText, context)
    }

    constructor(editText: EditText, attrs: AttributeSet, context: Context) {
        setupFromXml(editText, attrs, context)
    }

    private fun setupDynamically(editText: EditText, context: Context) {
        attrInfo.validationType = XmlValidationType.NOT_EMPTY
        this.editText = editText
        setupValidator(context)
        setupChangeListener()
    }

    private fun setupFromXml(
            editText: EditText,
            attrs: AttributeSet,
            context: Context
    ) {
        setupAttrs(attrs, context)
        this.editText = editText
        setupValidator(context)
        setupChangeListener()
    }

    private fun setupAttrs(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditText)

        attrInfo.required = typedArray.getBoolean(R.styleable.FormEditText_required, true)
        attrInfo.validateOnChange = typedArray.getBoolean(R.styleable.FormEditText_validateOnChange, false)

        val validationTypeValue = typedArray.getInt(R.styleable.FormEditText_validationType, XmlValidationType.UNKNOWN.value)
        attrInfo.validationType = XmlValidationType.fromValue(validationTypeValue)

        attrInfo.errorMessage = typedArray.getString(R.styleable.FormEditText_errorMessage) ?: ""
        attrInfo.customValidationType = typedArray.getString(R.styleable.FormEditText_customValidationType) ?: ""
        attrInfo.regex = typedArray.getString(R.styleable.FormEditText_regex) ?: ""
        attrInfo.emptyErrorMessage = typedArray.getString(R.styleable.FormEditText_requiredErrorMessage) ?: ""
        attrInfo.dateFormat = typedArray.getString(R.styleable.FormEditText_dateFormat) ?: ""

        when (attrInfo.validationType) {
            XmlValidationType.NUMERIC_RANGE -> {
                attrInfo.minNumber = typedArray.getInt(R.styleable.FormEditText_minNumber, Integer.MIN_VALUE).toLong()
                attrInfo.maxNumber = typedArray.getInt(R.styleable.FormEditText_maxNumber, Integer.MAX_VALUE).toLong()
            }

            XmlValidationType.FLOAT_NUMERIC_RANGE -> {
                attrInfo.floatMinNumber = typedArray.getFloat(R.styleable.FormEditText_floatMinNumber, Float.MIN_VALUE)
                attrInfo.floatMaxNumber = typedArray.getFloat(R.styleable.FormEditText_floatMaxNumber, Float.MAX_VALUE)
            }
            else -> {}
        }

        typedArray.recycle()
    }

    private fun setupChangeListener() {
        editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mValidator.value = s.toString()
                if (!attrInfo.validateOnChange) return
                validate()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun addValidator(validator: Validator<String>) {
        mValidator + validator
    }

    override fun setupValidator(context: Context) {
        mValidator = AllValidator()
        val validator = XmlValidatorFactory.make(attrInfo, context)
        validator.value = editText.text.toString()
        addValidator(validator)
    }

    override fun validate(showError: Boolean): Boolean {
        val isValid = mValidator.isValid
        if (!isValid && showError) showError()
        if(isValid) {
            editText.error = null
            textInputLayout()?.error = null
        }
        return isValid
    }

    override fun showError() {
        val e = mValidator.errorGenerator.generate()
        if (e.isEmpty()) return

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
