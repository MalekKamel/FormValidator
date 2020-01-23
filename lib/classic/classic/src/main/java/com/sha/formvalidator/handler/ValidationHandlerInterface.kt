package com.sha.formvalidator.handler

import android.content.Context
import android.util.AttributeSet
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.ValidationType
import com.sha.formvalidator.core.R

import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.mandatory

/**
 * Interface for encapsulating validation of an EditText control
 */
interface ValidationHandlerInterface<V> {
    var validator: AllValidator<V>
    var attrValidatorFactory: AttrValidatorFactory<V>

    fun onValueChanged(newValue: V) {
        validator.value = newValue
    }

    val value: V
    fun setupValidator(attrInfo: AttrInfo?, context: Context) {}

    fun setup(attrs: AttributeSet?, context: Context): AttrInfo? {
        validator = AllValidator()

        if (attrs == null) {
            setupValidator(null, context)
            return null
        }

        val attrInfo = createAttrInfo(attrs, context)
        createValidator(attrInfo, context)
        setupValidator(attrInfo, context)
        return attrInfo
    }

    fun createAttrInfo(attrs: AttributeSet?, context: Context): AttrInfo {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormValidator)
        val attrInfo = AttrInfo()

        attrInfo.required = typedArray.getBoolean(R.styleable.FormValidator_required, true)
        attrInfo.validateOnChange = typedArray.getBoolean(R.styleable.FormValidator_validateOnChange, false)

        val validationTypeValue = typedArray.getInt(R.styleable.FormValidator_validationType, ValidationType.UNKNOWN.value)
        attrInfo.validationType = ValidationType.fromValue(validationTypeValue)

        attrInfo.errorMessage = typedArray.getString(R.styleable.FormValidator_errorMessage) ?: ""
        attrInfo.customValidationType = typedArray.getString(R.styleable.FormValidator_customValidationType) ?: ""
        attrInfo.regex = typedArray.getString(R.styleable.FormValidator_regex) ?: ""
        attrInfo.dateFormat = typedArray.getString(R.styleable.FormValidator_dateFormat) ?: ""

        when (attrInfo.validationType) {
            ValidationType.INT_RANGE, ValidationType.TEXT_LENGTH -> {
                attrInfo.min = typedArray.getInt(R.styleable.FormValidator_min, Integer.MIN_VALUE)
                attrInfo.max = typedArray.getInt(R.styleable.FormValidator_max, Integer.MAX_VALUE)
            }

            ValidationType.FLOAT_RANGE -> {
                attrInfo.floatMin = typedArray.getFloat(R.styleable.FormValidator_floatMin, Float.MIN_VALUE)
                attrInfo.floatMax = typedArray.getFloat(R.styleable.FormValidator_floatMax, Float.MAX_VALUE)
            }
            else-> {}
        }
        typedArray.recycle()
        return attrInfo
    }

    private fun createValidator(attrInfo: AttrInfo, context: Context) {
        if (attrInfo.required) validator + mandatory()
        validator + attrValidatorFactory.make(attrInfo, context)
        validator.value = value
    }

    /**
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     *
     * @param validator
     */
    fun addValidator(validator: Validator<V>) {
        this.validator + validator
    }

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call [.Validator.isValid]
     *
     * @param showError determines if this call should show the UI error.
     * @return true if the validity passes false otherwise.
     */
    fun validate(): Boolean {
        val isValid = validator.isValid

        if (isValid) showError(null)
        else showError(validator.errorGenerator.generate())

        return isValid
    }


    fun showError(e: String?)

}
