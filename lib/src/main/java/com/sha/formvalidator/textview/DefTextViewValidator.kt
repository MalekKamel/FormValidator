package com.sha.formvalidator.textview

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sha.formvalidator.R
import com.sha.formvalidator.textview.validator.Validator
import com.sha.formvalidator.textview.validator.composite.AndValidator
import com.sha.formvalidator.textview.validator.composite.CompositeValidator

/**
 * Default implementation of an [TextViewValidator]
 */
class DefTextViewValidator : TextViewValidator {
    /**
     * The custom validators set using
     */
    private lateinit var mValidator: CompositeValidator
    lateinit var editText: EditText

    private val attrInfo = TextViewAttrInfo()

    val regex: String?
        get() = attrInfo.regex

    var errorMessage: String
        get() = attrInfo.errorMessage
        set(errorString) {
            attrInfo.errorMessage = errorString
            setupValidator(editText.context)
        }

    var validationType: TextViewValidationType?
        get() = attrInfo.validationType
        set(validationType) {
            attrInfo.validationType = validationType
            setupValidator(editText.context)
        }

    override val isRequired: Boolean
        get() = attrInfo.required

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
        attrInfo.validationType = TextViewValidationType.NOT_EMPTY
        this.editText = editText
        setupValidator(context)
    }

    private fun setupFromXml(
            editText: EditText,
            attrs: AttributeSet,
            context: Context
    ) {
        setupAttrs(attrs, context)
        this.editText = editText
        setupValidator(context)
    }

    private fun setupAttrs(attrs: AttributeSet, context: Context) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormEditText)

        attrInfo.required = typedArray.getBoolean(R.styleable.FormEditText_required, true)

        val validationTypeValue = typedArray.getInt(R.styleable.FormEditText_validationType, TextViewValidationType.NOT_DETECTABLE.value)
        attrInfo.validationType = TextViewValidationType.fromValue(validationTypeValue)

        attrInfo.errorMessage = typedArray.getString(R.styleable.FormEditText_errorMessage) ?: ""
        attrInfo.customValidationType = typedArray.getString(R.styleable.FormEditText_customValidationType) ?: ""
        attrInfo.regex = typedArray.getString(R.styleable.FormEditText_regex) ?: ""
        attrInfo.emptyErrorMessage = typedArray.getString(R.styleable.FormEditText_requiredErrorMessage) ?: ""
        attrInfo.dateFormat = typedArray.getString(R.styleable.FormEditText_dateFormat) ?: ""

        when (attrInfo.validationType) {
            TextViewValidationType.NUMERIC_RANGE -> {
                attrInfo.minNumber = typedArray.getInt(R.styleable.FormEditText_minNumber, Integer.MIN_VALUE)
                attrInfo.maxNumber = typedArray.getInt(R.styleable.FormEditText_maxNumber, Integer.MAX_VALUE)
            }

            TextViewValidationType.FLOAT_NUMERIC_RANGE -> {
                attrInfo.floatMinNumber = typedArray.getFloat(R.styleable.FormEditText_floatMinNumber, java.lang.Float.MIN_VALUE)
                attrInfo.floatMaxNumber = typedArray.getFloat(R.styleable.FormEditText_floatMaxNumber, java.lang.Float.MAX_VALUE)
            }
            else -> {}
        }

        typedArray.recycle()
    }

    override fun addValidator(validator: Validator) {
        mValidator.enqueue(validator)
    }

    override fun setupValidator(context: Context) {
        mValidator = AndValidator()
        addValidator(TextViewValidatorFactory.validator(attrInfo, context))
    }

    override fun validate(showError: Boolean): Boolean {
        val isValid = mValidator.isValid(editText)

        if (!isValid && showError) showError()

        return isValid
    }

    override fun showError() {
        if (!mValidator.hasErrorMessage()) return

        if (hasTextInputLayout()) {
            textInputLayout().error = mValidator.errorMessage
            return
        }

        editText.error = mValidator.errorMessage
    }

    private fun hasTextInputLayout(): Boolean {
        val parent = editText.parent.parent
        return parent is TextInputLayout
    }

    private fun textInputLayout(): TextInputLayout {
        return editText.parent.parent as TextInputLayout
    }

    // >>>>>>>>>> getters & setters

    fun setRegex(regex: String, context: Context) {
        attrInfo.validationType = TextViewValidationType.REGEX
        attrInfo.regex = regex
        setupValidator(context)
    }

    fun setRequired(required: Boolean, context: Context) {
        attrInfo.required = required
        setupValidator(context)
    }
}
