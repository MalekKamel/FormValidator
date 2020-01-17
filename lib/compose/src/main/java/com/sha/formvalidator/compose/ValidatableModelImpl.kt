package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

class MandatoryValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { MandatoryValidator() }
}

class OptionalValidation: AbstractStringModel() {
    override val validator by lazy { DummyValidator() }
}

class WebUrlValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { WebUrlValidator() }
}

class PhoneValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PhoneValidator() }
}

class PersonNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonNameValidator() }
}

class PersonFullNameValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator() }
}

class PatternValidation(pattern: Pattern): AbstractStringModel() {
    override val validator: TextValidator by lazy { PatternValidator(pattern) }
}

class IpAddressValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { IpAddressValidator() }
}

class EmailValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { EmailValidator() }
}

class DomainValidatorValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { DomainValidator() }
}

class AlphaValidation: AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaValidator() }
}

class AlphaNumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator() }
}

class OrValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { OrValidator(validators) }
}

class AndValidation(validators: List<TextValidator>) : AbstractStringModel() {
    override val validator: TextValidator by lazy { AndValidator(validators) }
}

class ValueMatchValidation(models: List<ValidatableModel<String>>) : AbstractStringModel() {
    override var validator: Validator<String> =
            ValueMatchValidator { models.map { it.value } }
                    .apply {
                        onError = { models.forEach { it.showError(errorMessage) } }
                    }
}

class PrefixValidation(prefix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix) }
}

class SuffixValidation(suffix: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix) }
}

class NumericValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { NumericValidator() }
}

class NumericRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy {
        WrapTextValidator(LongRangeValidator(min, max)) { it.toLong() }
    }
}

class LengthRangeValidation(min: Long, max: Long) : AbstractStringModel() {
    override val validator: TextValidator by lazy { LengthRangeTextValidator(min, max) }
}

class DateValidation(format: String) : AbstractStringModel() {
    override val validator: TextValidator by lazy { DateValidator(format) }
}

class CreditCardValidation : AbstractStringModel() {
    override val validator: TextValidator by lazy { CreditCardValidator() }
}

class BooleanValidation(validation: Boolean) : AbstractBooleanModel() {
    override val validator: Validator<Boolean> = BooleanValidator(validation)
}

class DoubleRangeValidation(min: Double, max: Double) : AbstractDoubleModel() {
    override val validator: Validator<Double> by lazy { DoubleRangeValidator(min, max) }
}

class FloatRangeValidation(min: Float, max: Float) : AbstractFloatModel() {
    override val validator: Validator<Float> by lazy { FloatRangeValidator(min, max) }
}

class LongRangeValidation(min: Long, max: Long) : AbstractLongModel() {
    override val validator: Validator<Long> by lazy { LongRangeValidator(min, max) }
}

class IntRangeValidation(min: Int, max: Int) : AbstractIntModel() {
    override val validator: Validator<Int> by lazy { IntRangeValidator(min, max) }
}

class CharRangeValidation(min: Char, max: Char) : AbstractCharModel() {
    override val validator: Validator<Char> by lazy { CharRangeValidator(min, max) }
}

class ShortRangeValidation(min: Short, max: Short) : AbstractShortModel() {
    override val validator: Validator<Short> by lazy { ShortRangeValidator(min, max) }
}

class ByteRangeValidation(min: Byte, max: Byte) : AbstractByteModel() {
    override val validator: Validator<Byte> by lazy { ByteRangeValidator(min, max) }
}