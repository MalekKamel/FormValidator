package com.sha.formvalidator.compose

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

class MandatoryValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { RequiredValidator(errorText) }
    override var errorText = DefaultErrors.mandatoryError
}

class OptionalValidation: AbstractStringModel() {
    override val validator = object : TextValidator() { override fun isValid(value: String) = true }
    override var errorText: String = ""
}

class WebUrlValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { WebUrlValidator(errorText) }
    override var errorText = DefaultErrors.webUrlError
}

class PhoneValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PhoneValidator(errorText) }
    override var errorText = DefaultErrors.phoneError
}

class PersonNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonNameValidator(errorText) }
    override var errorText = DefaultErrors.personNameError
}

class PersonFullNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator(errorText) }
    override var errorText = DefaultErrors.personFullNameError
}

class PatternValidation(pattern: Pattern): AbstractStringModel() {
    override val validator: TextValidator by lazy { PatternValidator(errorText, pattern) }
    override var errorText = DefaultErrors.patternError
}

class IpAddressValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { IpAddressValidator(errorText) }
    override var errorText = DefaultErrors.ipAddressError
}

class EmailValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { EmailValidator(errorText) }
    override var errorText = DefaultErrors.emailError
}

class DomainValidatorValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { DomainValidator(errorText) }
    override var errorText = DefaultErrors.domainError
}

class AlphaValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaValidator(errorText) }
    override var errorText = DefaultErrors.alphaError
}

class AlphaNumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator(errorText) }
    override var errorText = DefaultErrors.alphaNumericError
}

class OrValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { OrValidator(errorText, validators) }
    override var errorText = DefaultErrors.orError
}

class AndValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { AndValidator(validators) }
    override var errorText = ""
}

class ValueMatchValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { ValueMatchValidator(errorText) }
    override var errorText = DefaultErrors.valueMatchError
}

class PrefixValidation(prefix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class SuffixValidation(suffix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class NumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { NumericValidator(errorText) }
    override var errorText = DefaultErrors.numericError
}

class NumericRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { NumericRangeValidator(min, max, errorText) }
    override var errorText = DefaultErrors.numericRangeError
}

class LengthRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { LengthRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.lengthRangeError
}

class FloatNumericRangeValidation(min: Double, max: Double) : AbstractStringModel() {
    override val validator: TextValidator by lazy { FloatNumericRangeValidator(min, max, errorText) }
    override var errorText = DefaultErrors.floatNumericRangeError
}

class DateValidation(format: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { DateValidator(errorText, format) }
    override var errorText = DefaultErrors.dateError
}

class CreditCardValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { CreditCardValidator(errorText) }
    override var errorText = DefaultErrors.creditCardError
}

class BooleanValidation(validation: Boolean) : AbstractBooleanModel() {
    override val validator: Validator<Boolean> = BooleanValidator(validation)
    override var errorText = DefaultErrors.booleanError
}

class RangeValidation(private val min: Long, private val max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { NumericRangeValidator(min, max, errorText) }
    override var errorText = DefaultErrors.booleanError
}
