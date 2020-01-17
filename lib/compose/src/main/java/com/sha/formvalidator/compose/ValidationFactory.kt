package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.TextValidator
import java.util.regex.Pattern

object Validation {

    fun mandatory(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(MandatoryValidation(), compositeValidation, block)

    fun optional(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(OptionalValidation(), compositeValidation, block)

    fun webUrl(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(WebUrlValidation(), compositeValidation, block)

    fun phone(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PhoneValidation(), compositeValidation, block)

    fun personName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonNameValidation(), compositeValidation, block)

    fun personFullName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonFullNameValidation(), compositeValidation, block)

    fun ipAddress(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(IpAddressValidation(), compositeValidation, block)

    fun email(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(EmailValidation(), compositeValidation, block)

    fun domain(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DomainValidatorValidation(), compositeValidation, block)

    fun alpha(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaValidation(), compositeValidation, block)

    fun alphaNumeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaNumericValidation(), compositeValidation, block)

    fun valueMatch(
            models: List<ValidatableModel<String>>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(ValueMatchValidation(models), compositeValidation, block)

    fun numeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(NumericValidation(), compositeValidation, block)

    fun creditCard(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(CreditCardValidation(), compositeValidation, block)

    fun pattern(
            pattern: Pattern,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PatternValidation(pattern), compositeValidation, block)

    fun or(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(OrValidation(validators), compositeValidation, block)

    fun and(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AndValidation(validators), compositeValidation, block)

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PrefixValidation(prefix), compositeValidation, block)

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(SuffixValidation(suffix), compositeValidation, block)

    fun date(
            format: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DateValidation(format), compositeValidation, block)

    fun longTextRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(NumericRangeValidation(min, max), compositeValidation, block)

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(LengthRangeValidation(min, max), compositeValidation, block)

    fun floatRange(
            min: Float,
            max: Float,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Float>.() -> Unit)? = null
    ): ValidatableModel<Float> = makeModel(FloatRangeValidation(min, max), compositeValidation, block)

    fun doubleRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Double>.() -> Unit)? = null
    ): ValidatableModel<Double> = makeModel(DoubleRangeValidation(min, max), compositeValidation, block)

    fun longRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Long>.() -> Unit)? = null
    ): ValidatableModel<Long> = makeModel(LongRangeValidation(min, max), compositeValidation, block)

    fun intRange(
            min: Int,
            max: Int,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Int>.() -> Unit)? = null
    ): ValidatableModel<Int> = makeModel(IntRangeValidation(min, max), compositeValidation, block)

    fun charRange(
            min: Char,
            max: Char,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Char>.() -> Unit)? = null
    ): ValidatableModel<Char> = makeModel(CharRangeValidation(min, max), compositeValidation, block)

    fun shortRange(
            min: Short,
            max: Short,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Short>.() -> Unit)? = null
    ): ValidatableModel<Short> = makeModel(ShortRangeValidation(min, max), compositeValidation, block)

    fun byteRange(
            min: Byte,
            max: Byte,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Byte>.() -> Unit)? = null
    ): ValidatableModel<Byte> = makeModel(ByteRangeValidation(min, max), compositeValidation, block)

    fun boolean(
            validation: Boolean,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Boolean>.() -> Unit)? = null
    ): ValidatableModel<Boolean> = makeModel(BooleanValidation(validation), compositeValidation, block)

    fun <T: Validatable> makeModel(
            model: T,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (T.() -> Unit)? = null
    ): T {
        return model.apply {
            block?.invoke(this)
            compositeValidation?.add(this)
        }
    }
}