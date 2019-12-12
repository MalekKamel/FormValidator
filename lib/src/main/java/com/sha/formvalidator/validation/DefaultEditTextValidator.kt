package com.sha.formvalidator.validation

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.sha.formvalidator.R
import com.sha.formvalidator.model.Options
import com.sha.formvalidator.model.ValidationType
import com.sha.formvalidator.util.ImplicitValidatorFactory
import com.sha.formvalidator.validator.Validator
import com.sha.formvalidator.validator.composite.AndValidator
import com.sha.formvalidator.validator.composite.CompositeValidator

/**
 * Default implementation of an [EditTextValidator]
 */
class DefaultEditTextValidator : EditTextValidator {
    /**
     * The custom validators set using
     */
    private lateinit var mValidator: CompositeValidator
    lateinit var editText: EditText

    private val options = Options()

    // might sound like a bug. but there's no way to know if the error is shown (not with public api)
    val isErrorShown: Boolean
        get() {
            return try {
                val parent = editText.parent as TextInputLayout
                true
            } catch (e: Throwable) {
                !TextUtils.isEmpty(editText.error)
            }

        }

    val regex: String?
        get() = options.regex

    var errorMessage: String
        get() = options.errorMessage
        set(errorString) {
            options.errorMessage = errorString
            setupValidator(editText.context)
        }

    var validationType: ValidationType?
        get() = options.validationType
        set(validationType) {
            options.validationType = validationType
            setupValidator(editText.context)
        }

    override val isRequired: Boolean
        get() = options.required

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
        options.validationType = ValidationType.NOT_EMPTY
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

        options.required = typedArray.getBoolean(R.styleable.FormEditText_required, true)

        val validationTypeValue = typedArray.getInt(R.styleable.FormEditText_validationType, ValidationType.NOT_DETECTABLE.value)
        options.validationType = ValidationType.fromValue(validationTypeValue)

        options.errorMessage = typedArray.getString(R.styleable.FormEditText_errorMessage) ?: ""
        options.customValidationType = typedArray.getString(R.styleable.FormEditText_customValidationType) ?: ""
        options.regex = typedArray.getString(R.styleable.FormEditText_regex) ?: ""
        options.emptyErrorMessage = typedArray.getString(R.styleable.FormEditText_requiredErrorMessage) ?: ""
        options.dateFormat = typedArray.getString(R.styleable.FormEditText_dateFormat) ?: ""

        when (options.validationType) {
            ValidationType.NUMERIC_RANGE -> {
                options.minNumber = typedArray.getInt(R.styleable.FormEditText_minNumber, Integer.MIN_VALUE)
                options.maxNumber = typedArray.getInt(R.styleable.FormEditText_maxNumber, Integer.MAX_VALUE)
            }

            ValidationType.FLOAT_NUMERIC_RANGE -> {
                options.floatMinNumber = typedArray.getFloat(R.styleable.FormEditText_floatMinNumber, java.lang.Float.MIN_VALUE)
                options.floatMaxNumber = typedArray.getFloat(R.styleable.FormEditText_floatMaxNumber, java.lang.Float.MAX_VALUE)
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

        addValidator(ImplicitValidatorFactory.validator(options, context))
    }

    override fun validate(): Boolean {
        return validate(true)
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
        options.validationType = ValidationType.REGEX
        options.regex = regex
        setupValidator(context)
    }

    fun setRequired(required: Boolean, context: Context) {
        options.required = required
        setupValidator(context)
    }
}
