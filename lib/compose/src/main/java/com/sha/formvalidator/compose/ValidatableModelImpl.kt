package com.sha.formvalidator.compose

import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

abstract class AbstractValidatableTextModel: ValidatableModel {
    override var text: String = ""
        set(value) {
            field = value
            isValid = validator.isValid(value)
        }
    abstract val validator: TextValidator
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var forceValidationOnce: Boolean = false
    override var recompose: () -> Unit = {}
    override var errorTextRes: Int = -1
        set(value) {
            field = value
            val context = +ambient(ContextAmbient)
            errorText = context.getString(value)
        }
}

class MandatoryValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { RequiredValidator(errorText) }
    override var errorText = DefaultErrors.mandatoryError
}

class OptionalValidation: AbstractValidatableTextModel() {
    override val validator = object : TextValidator() { override fun isValid(text: String) = true }
    override var errorText: String = ""
}

class WebUrlValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { WebUrlValidator(errorText) }
    override var errorText = DefaultErrors.webUrlError
}

class PhoneValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PhoneValidator(errorText) }
    override var errorText = DefaultErrors.phoneError
}

class PersonNameValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonNameValidator(errorText) }
    override var errorText = DefaultErrors.personNameError
}

class PersonFullNameValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator(errorText) }
    override var errorText = DefaultErrors.personFullNameError
}

class PatternValidation(pattern: Pattern): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PatternValidator(errorText, pattern) }
    override var errorText = DefaultErrors.patternError
}

class IpAddressValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { IpAddressValidator(errorText) }
    override var errorText = DefaultErrors.ipAddressError
}

class EmailValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { EmailValidator(errorText) }
    override var errorText = DefaultErrors.emailError
}

class DomainValidatorValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DomainValidator(errorText) }
    override var errorText = DefaultErrors.domainError
}

class AlphaValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaValidator(errorText) }
    override var errorText = DefaultErrors.alphaError
}

class AlphaNumericValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator(errorText) }
    override var errorText = DefaultErrors.alphaNumericError
}

class OrValidation(validators: List<TextValidator>) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { OrValidator(errorText, validators) }
    override var errorText = DefaultErrors.orError
}

class AndValidation(validators: List<TextValidator>) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AndValidator(validators) }
    override var errorText = ""
}

class ValueMatchValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { ValueMatchValidator(errorText) }
    override var errorText = DefaultErrors.valueMatchError
}

class PrefixValidation(prefix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class SuffixValidation(suffix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class NumericValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericValidator(errorText) }
    override var errorText = DefaultErrors.numericError
}

class NumericRangeValidation(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.numericRangeError
}

class LengthRangeValidation(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { LengthRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.lengthRangeError
}

class FloatNumericRangeValidation(min: Double, max: Double) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { FloatNumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.floatNumericRangeError
}

class DateValidation(format: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DateValidator(errorText, format) }
    override var errorText = DefaultErrors.dateError
}

class CreditCardValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { CreditCardValidator(errorText) }
    override var errorText = DefaultErrors.creditCardError
}
