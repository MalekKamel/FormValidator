package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.AnyValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

object ModelFactory {
    fun <V> make(
            validator: Validator<V>,
            block: (ValidationModel<V>.() -> Unit)? = null
    ): ValidationModel<V> {
        val model = ValidationModel.create(validator, block)

        // DummyValidator means it's optional
        if (validator is DummyValidator) return model
        // no need to add another MandatoryValidator
        if (validator is MandatoryValidator) return model
        // it's optional
        if (!model.isMandatory) return model

        // add MandatoryValidator
        model.validator.validators.add(0, MandatoryValidator())

        return model
    }
}

fun <V> make(
        validator: Validator<V>,
        block: (ValidationModel<V>.() -> Unit)? = null
): ValidationModel<V> = ModelFactory.make(validator, block)

fun <V> mandatory(
        invalidValue: V? = null,
        block: (ValidatableModel<V>.() -> Unit)? = null
): ValidatableModel<V> = make(MandatoryValidator(invalidValue), block)

fun optional(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(DummyValidator(), block)

fun webUrl(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(WebUrlValidator(), block)

fun phone(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PhoneValidator(), block)

fun personName(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PersonNameValidator(), block)

fun personFullName(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PersonFullNameValidator(), block)

fun ipAddress(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(IpAddressValidator(), block)

fun email(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(EmailValidator(), block)

fun domain(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(DomainValidator(), block)

fun alpha(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(AlphaValidator(), block)

fun alphaNumeric(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(AlphaNumericValidator(), block)

fun numeric(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(NumericValidator(), block)

fun creditCard(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(CreditCardValidator(), block)

fun pattern(
        pattern: Pattern,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PatternValidator(pattern), block)

fun pattern(
        pattern: String,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PatternValidator(pattern), block)

fun <V> any(
        vararg models: ValidatableModel<V>,
        block: (ValidatableModel<V>.() -> Unit)? = null
): ValidatableModel<V> = make(AnyValidator(models.map { it.validator }), block)

fun <V> all(
        vararg models: ValidatableModel<V>,
        block: (ValidatableModel<V>.() -> Unit)? = null
): ValidatableModel<V> = make(AllValidator(models.map { it.validator }), block)

fun prefix(
        prefix: String,
        ignoreCase: Boolean = false,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(PrefixValidator(prefix, ignoreCase), block)

fun suffix(
        suffix: String,
        ignoreCase: Boolean = false,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(SuffixValidator(suffix, ignoreCase), block)

fun date(
        format: String,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(DateValidator(format), block)

fun textLength(
        min: Long,
        max: Long,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = make(
        WrapValidator(LongRangeValidator(min, max)) { it?.length?.toLong() },
        block
)

fun floatRange(
        min: Float,
        max: Float,
        block: (ValidatableModel<Float>.() -> Unit)? = null
): ValidatableModel<Float> = make(FloatRangeValidator(min, max), block)

fun doubleRange(
        min: Double,
        max: Double,
        block: (ValidatableModel<Double>.() -> Unit)? = null
): ValidatableModel<Double> = make(DoubleRangeValidator(min, max), block)

fun longRange(
        min: Long,
        max: Long,
        block: (ValidatableModel<Long>.() -> Unit)? = null
): ValidatableModel<Long> = make(LongRangeValidator(min, max), block)

fun intRange(
        min: Int,
        max: Int,
        block: (ValidatableModel<Int>.() -> Unit)? = null
): ValidatableModel<Int> = make(IntRangeValidator(min, max), block)

fun charRange(
        min: Char,
        max: Char,
        block: (ValidatableModel<Char>.() -> Unit)? = null
): ValidatableModel<Char> = make(CharRangeValidator(min, max), block)

fun shortRange(
        min: Short,
        max: Short,
        block: (ValidatableModel<Short>.() -> Unit)? = null
): ValidatableModel<Short> = make(ShortRangeValidator(min, max), block)

fun byteRange(
        min: Byte,
        max: Byte,
        block: (ValidatableModel<Byte>.() -> Unit)? = null
): ValidatableModel<Byte> = make(ByteRangeValidator(min, max), block)

fun boolean(
        validation: Boolean,
        block: (ValidatableModel<Boolean>.() -> Unit)? = null
): ValidatableModel<Boolean> = make(BooleanValidator { validation }, block)

fun condition(
        condition: () -> Boolean,
        block: (ValidatableModel<Boolean>.() -> Unit)? = null
): ValidatableModel<Boolean> = make(BooleanValidator(condition), block)
