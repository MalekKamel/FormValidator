package com.sha.formvalidator.handler

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.sha.formvalidator.FormErrorLayout
import com.sha.formvalidator.R
import com.sha.formvalidator.core.validator.InverseValidator
import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.Validator
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.OptionalValidator
import com.sha.formvalidator.factory.AttrValidatorFactory
import com.sha.formvalidator.mandatory
import com.sha.formvalidator.model.AttrInfo
import com.sha.formvalidator.model.InvalidIf
import com.sha.formvalidator.model.ValidationType

/**
 * Interface for encapsulating validation of an EditText control
 */
interface ValidationHandler<V> {
    var validator: AllValidator<V>
    var attrValidatorFactory: AttrValidatorFactory<V>
    val view: View
    val value: V
    var attrInfo: AttrInfo?

    fun setupValidator(attrInfo: AttrInfo?, context: Context) {}

    fun setup(attrs: AttributeSet?, context: Context): AttrInfo? {
        if (attrs == null) {
            setupValidator(null, context)
            return null
        }

        val attrInfo = createAttrInfo(attrs, context)
        createValidator()
        setupValidator(attrInfo, context)
        return attrInfo
    }

    fun createAttrInfo(attrs: AttributeSet?, context: Context): AttrInfo? {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormValidator)
        attrInfo = AttrInfo()

        attrInfo!!.required = typedArray.getBoolean(R.styleable.FormValidator_required, true)
        attrInfo!!.validateOnChange = typedArray.getBoolean(R.styleable.FormValidator_validateOnChange, false)

        val validationTypeValue = typedArray.getInt(R.styleable.FormValidator_validationType, ValidationType.UNKNOWN.value)
        attrInfo!!.validationType = ValidationType.fromValue(validationTypeValue)

        attrInfo!!.errorMessage = typedArray.getString(R.styleable.FormValidator_errorMessage) ?: ""
        attrInfo!!.regex = typedArray.getString(R.styleable.FormValidator_regex) ?: ""
        attrInfo!!.dateFormat = typedArray.getString(R.styleable.FormValidator_dateFormat) ?: ""

        attrInfo!!.min = typedArray.getInt(R.styleable.FormValidator_minInt, Integer.MIN_VALUE)
        attrInfo!!.max = typedArray.getInt(R.styleable.FormValidator_maxInt, Integer.MAX_VALUE)

        attrInfo!!.floatMin = typedArray.getFloat(R.styleable.FormValidator_minFloat, Float.MIN_VALUE)
        attrInfo!!.floatMax = typedArray.getFloat(R.styleable.FormValidator_maxFloat, Float.MAX_VALUE)

        val invalidIf = typedArray.getInt(R.styleable.FormValidator_invalidIf, InvalidIf.UNKNOWN.value)
        attrInfo!!.invalidIf = InvalidIf.fromValue(invalidIf)

        typedArray.recycle()
        return attrInfo
    }

    private fun createValidator() {
        validator = if(attrInfo == null || attrInfo!!.required) AllValidator() else OptionalValidator()

        attrInfo?.run {
            validator + attrValidatorFactory.make(this, view.context)
            if (errorMessage.isNotEmpty()) validator.errorMessage = this.errorMessage
        }

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
     * Add a validator to this FormEditText. The validator will be added in the
     * queue of the current validators.
     */
    fun addValidator(index: Int, other: Validator<V>) = validator.validators.add(index, other)

    /**
     * Calling *validate()* will cause the EditText to go through
     * customValidators and call [.Validator.isValid]
     *
     * @return true if the validity passes false otherwise.
     */
    fun validate(): Boolean {
        val isValid = validator.isValid

        val error = if (isValid) null else validator.errorGenerator.generate()
        val formErrorLayout = view.parent as? FormErrorLayout
        if (formErrorLayout != null) formErrorLayout.setError(error)
        else showError(error)

        return isValid
    }

    fun showError(e: String?)

    fun onValueChanged(newValue: V) {
        validator.value = newValue
    }

}

abstract class AbsValidationHandler<V> : ValidationHandler<V> {
    override var attrInfo: AttrInfo? = null
    override lateinit var validator: AllValidator<V>
}
