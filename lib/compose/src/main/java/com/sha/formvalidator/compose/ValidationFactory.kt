package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

object Validation {

    fun mandatory(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(MandatoryValidator(), compositeValidation, block)

    fun optional(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DummyValidator(), compositeValidation, block)

    fun webUrl(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(WebUrlValidator(), compositeValidation, block)

    fun phone(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PhoneValidator(), compositeValidation, block)

    fun personName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonNameValidator(), compositeValidation, block)

    fun personFullName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonFullNameValidator(), compositeValidation, block)

    fun ipAddress(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(IpAddressValidator(), compositeValidation, block)

    fun email(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(EmailValidator(), compositeValidation, block)

    fun domain(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DomainValidator(), compositeValidation, block)

    fun alpha(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaValidator(), compositeValidation, block)

    fun alphaNumeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaNumericValidator(), compositeValidation, block)

    fun <V> valueMatch(
            models: List<ValidatableModel<V>>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<V>.() -> Unit)? = null
    ): ValidatableModel<V> = makeModel(
            ValueMatchValidator { models.map { it.value } }
                    .apply {
                        onError = { models.forEach { it.showError(errorMessage) } }
                    },
            compositeValidation, block
    )

    fun numeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(NumericValidator(), compositeValidation, block)

    fun creditCard(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(CreditCardValidator(), compositeValidation, block)

    fun pattern(
            pattern: Pattern,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PatternValidator(pattern), compositeValidation, block)

    fun or(
            validators: List<TextValidator>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(OrValidator(validators), compositeValidation, block)

    fun and(
            validators: List<TextValidator>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AndValidator(validators), compositeValidation, block)

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PrefixValidator(prefix), compositeValidation, block)

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(SuffixValidator(suffix), compositeValidation, block)

    fun date(
            format: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DateValidator(format), compositeValidation, block)

    fun longTextRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(
            WrapValidator(LongRangeValidator(min, max)) { it?.toLong() },
            compositeValidation, block
    )

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(LengthRangeTextValidator(min, max), compositeValidation, block)

    fun floatRange(
            min: Float,
            max: Float,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Float>.() -> Unit)? = null
    ): ValidatableModel<Float> = makeModel(FloatRangeValidator(min, max), compositeValidation, block)

    fun doubleRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Double>.() -> Unit)? = null
    ): ValidatableModel<Double> = makeModel(DoubleRangeValidator(min, max), compositeValidation, block)

    fun longRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Long>.() -> Unit)? = null
    ): ValidatableModel<Long> = makeModel(LongRangeValidator(min, max), compositeValidation, block)

    fun intRange(
            min: Int,
            max: Int,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Int>.() -> Unit)? = null
    ): ValidatableModel<Int> = makeModel(IntRangeValidator(min, max), compositeValidation, block)

    fun charRange(
            min: Char,
            max: Char,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Char>.() -> Unit)? = null
    ): ValidatableModel<Char> = makeModel(CharRangeValidator(min, max), compositeValidation, block)

    fun shortRange(
            min: Short,
            max: Short,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Short>.() -> Unit)? = null
    ): ValidatableModel<Short> = makeModel(ShortRangeValidator(min, max), compositeValidation, block)

    fun byteRange(
            min: Byte,
            max: Byte,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Byte>.() -> Unit)? = null
    ): ValidatableModel<Byte> = makeModel(ByteRangeValidator(min, max), compositeValidation, block)

    fun boolean(
            validation: Boolean,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidatableModel<Boolean>.() -> Unit)? = null
    ): ValidatableModel<Boolean> = makeModel(BooleanValidator(validation), compositeValidation, block)

    fun <V> makeModel(
            validator: Validator<V>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValidationModel<V>.() -> Unit)? = null
    ): ValidationModel<V> {
        return ValidationModel(validator).apply {
            block?.invoke(this)
            compositeValidation?.add(this)
        }
    }
}

object Factory {

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