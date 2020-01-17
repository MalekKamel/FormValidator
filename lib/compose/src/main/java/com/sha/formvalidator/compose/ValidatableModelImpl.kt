package com.sha.formvalidator.compose

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

class MandatoryValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { RequiredValidator(errorMessage) }
    override var errorMessage = DefaultErrors.mandatoryError
}

class OptionalValidation: AbstractStringModel() {
    override val validator = object : TextValidator() { override fun validate() = true }
    override var errorMessage: String = ""
}

class WebUrlValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { WebUrlValidator(errorMessage) }
    override var errorMessage = DefaultErrors.webUrlError
}

class PhoneValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PhoneValidator(errorMessage) }
    override var errorMessage = DefaultErrors.phoneError
}

class PersonNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonNameValidator(errorMessage) }
    override var errorMessage = DefaultErrors.personNameError
}

class PersonFullNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator(errorMessage) }
    override var errorMessage = DefaultErrors.personFullNameError
}

class PatternValidation(pattern: Pattern): AbstractStringModel() {
    override val validator: TextValidator by lazy { PatternValidator(errorMessage, pattern) }
    override var errorMessage = DefaultErrors.patternError
}

class IpAddressValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { IpAddressValidator(errorMessage) }
    override var errorMessage = DefaultErrors.ipAddressError
}

class EmailValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { EmailValidator(errorMessage) }
    override var errorMessage = DefaultErrors.emailError
}

class DomainValidatorValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { DomainValidator(errorMessage) }
    override var errorMessage = DefaultErrors.domainError
}

class AlphaValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaValidator(errorMessage) }
    override var errorMessage = DefaultErrors.alphaError
}

class AlphaNumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator(errorMessage) }
    override var errorMessage = DefaultErrors.alphaNumericError
}

class OrValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { OrValidator(errorMessage, validators) }
    override var errorMessage = DefaultErrors.orError
}

class AndValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { AndValidator(validators) }
    override var errorMessage = ""
}

class ValueMatchValidation(vararg models: ValidatableModel<String>) : AbstractStringModel() {
    override val validator: Validator<String> by lazy {
        object : Validator<String> {
            override var value: String = ""
            override fun validate(): Boolean {
                if (models.isEmpty()) return true
                val firstValue = models.first().value
                val valid = models.all { it.value == firstValue }
                if (!valid) models.forEach { it.showError(errorMessage) }
                return valid
            }
        }
    }
    override var errorMessage = DefaultErrors.valueMatchError
}

class PrefixValidation(prefix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix, errorMessage) }
    override var errorMessage = DefaultErrors.suffixError
}

class SuffixValidation(suffix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix, errorMessage) }
    override var errorMessage = DefaultErrors.suffixError
}

class NumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { NumericValidator(errorMessage) }
    override var errorMessage = DefaultErrors.numericError
}

class NumericRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { LongRangeTextValidator(min, max, errorMessage) }
    override var errorMessage = DefaultErrors.numericRangeError
}

class LengthRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { LengthRangeTextValidator(errorMessage, min, max) }
    override var errorMessage = DefaultErrors.lengthRangeError
}

class DateValidation(format: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { DateValidator(errorMessage, format) }
    override var errorMessage = DefaultErrors.dateError
}

class CreditCardValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { CreditCardValidator(errorMessage) }
    override var errorMessage = DefaultErrors.creditCardError
}

class BooleanValidation(validation: Boolean) : AbstractBooleanModel() {
    override val validator: Validator<Boolean> = BooleanValidator(validation)
    override var errorMessage = DefaultErrors.booleanError
}

class DoubleRangeValidation(min: Double, max: Double) : AbstractDoubleModel() {
    override val validator: Validator<Double> by lazy { DoubleRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class FloatRangeValidation(min: Float, max: Float) : AbstractFloatModel() {
    override val validator: Validator<Float> by lazy { FloatRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class LongRangeValidation(min: Long, max: Long) : AbstractLongModel() {
    override val validator: Validator<Long> by lazy { LongRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class IntRangeValidation(min: Int, max: Int) : AbstractIntModel() {
    override val validator: Validator<Int> by lazy { IntRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class CharRangeValidation(min: Char, max: Char) : AbstractCharModel() {
    override val validator: Validator<Char> by lazy { CharRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class ShortRangeValidation(min: Short, max: Short) : AbstractShortModel() {
    override val validator: Validator<Short> by lazy { ShortRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}

class ByteRangeValidation(min: Byte, max: Byte) : AbstractByteModel() {
    override val validator: Validator<Byte> by lazy { ByteRangeValidator(min, max) }
    override var errorMessage = DefaultErrors.rangeError
}