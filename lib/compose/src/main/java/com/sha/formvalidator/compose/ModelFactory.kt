package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.AnyValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

object ModelFactory {

    fun mandatory(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(MandatoryValidator(), block)

    fun optional(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DummyValidator(), block)

    fun webUrl(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(WebUrlValidator(), block)

    fun phone(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PhoneValidator(), block)

    fun personName(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonNameValidator(), block)

    fun personFullName(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PersonFullNameValidator(), block)

    fun ipAddress(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(IpAddressValidator(), block)

    fun email(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(EmailValidator(), block)

    fun domain(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DomainValidator(), block)

    fun alpha(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaValidator(), block)

    fun alphaNumeric(

            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(AlphaNumericValidator(), block)

    fun numeric(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(NumericValidator(), block)

    fun creditCard(
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(CreditCardValidator(), block)

    fun pattern(
            pattern: Pattern,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PatternValidator(pattern), block)

    fun <V> any(
            models: List<ValidatableModel<V>>,
            block: (ValidatableModel<V>.() -> Unit)? = null
    ): ValidatableModel<V> = makeModel(AnyValidator(models.map { it.validator }), block)

    fun <V> all(
            models: List<ValidatableModel<V>>,
            block: (ValidatableModel<V>.() -> Unit)? = null
    ): ValidatableModel<V> = makeModel(AllValidator(models.map { it.validator }), block)

    fun prefix(
            prefix: String,
            ignoreCase: Boolean = false,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(PrefixValidator(prefix, ignoreCase), block)

    fun suffix(
            suffix: String,
            ignoreCase: Boolean = false,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(SuffixValidator(suffix, ignoreCase), block)

    fun date(
            format: String,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(DateValidator(format), block)

    fun textLength(
            min: Long,
            max: Long,
            block: (ValidatableModel<String>.() -> Unit)? = null
    ): ValidatableModel<String> = makeModel(
            WrapValidator(LongRangeValidator(min, max)) { it?.length?.toLong() },
            block
    )

    fun floatRange(
            min: Float,
            max: Float,
            block: (ValidatableModel<Float>.() -> Unit)? = null
    ): ValidatableModel<Float> = makeModel(FloatRangeValidator(min, max), block)

    fun doubleRange(
            min: Double,
            max: Double,
            block: (ValidatableModel<Double>.() -> Unit)? = null
    ): ValidatableModel<Double> = makeModel(DoubleRangeValidator(min, max), block)

    fun longRange(
            min: Long,
            max: Long,
            block: (ValidatableModel<Long>.() -> Unit)? = null
    ): ValidatableModel<Long> = makeModel(LongRangeValidator(min, max), block)

    fun intRange(
            min: Int,
            max: Int,
            block: (ValidatableModel<Int>.() -> Unit)? = null
    ): ValidatableModel<Int> = makeModel(IntRangeValidator(min, max), block)

    fun charRange(
            min: Char,
            max: Char,
            block: (ValidatableModel<Char>.() -> Unit)? = null
    ): ValidatableModel<Char> = makeModel(CharRangeValidator(min, max), block)

    fun shortRange(
            min: Short,
            max: Short,
            block: (ValidatableModel<Short>.() -> Unit)? = null
    ): ValidatableModel<Short> = makeModel(ShortRangeValidator(min, max), block)

    fun byteRange(
            min: Byte,
            max: Byte,
            block: (ValidatableModel<Byte>.() -> Unit)? = null
    ): ValidatableModel<Byte> = makeModel(ByteRangeValidator(min, max), block)

    fun boolean(
            validation: Boolean,
            block: (ValidatableModel<Boolean>.() -> Unit)? = null
    ): ValidatableModel<Boolean> = makeModel(BooleanValidator { validation }, block)

    fun <V> makeModel(
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

fun mandatory(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.mandatory(block)

fun optional(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.optional(block)

fun webUrl(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.webUrl(block)

fun phone(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.phone(block)

fun personName(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.personName(block)

fun personFullName(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.personFullName(block)

fun ipAddress(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.ipAddress(block)

fun email(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.email(block)

fun domain(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.domain(block)

fun alpha(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.alpha(block)

fun alphaNumeric(

        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.alphaNumeric(block)

fun numeric(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.numeric(block)

fun creditCard(
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.creditCard(block)

fun pattern(
        pattern: Pattern,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.pattern(pattern, block)

fun <V> any(
        vararg models: ValidatableModel<V>,
        block: (ValidatableModel<V>.() -> Unit)? = null
): ValidatableModel<V> = ModelFactory.any(models.asList(), block)

fun <V> all(
        vararg models: ValidatableModel<V>,
        block: (ValidatableModel<V>.() -> Unit)? = null
): ValidatableModel<V> = ModelFactory.all(models.asList(), block)

fun prefix(
        prefix: String,
        ignoreCase: Boolean = false,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.prefix(prefix, ignoreCase, block)

fun suffix(
        suffix: String,
        ignoreCase: Boolean = false,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.suffix(suffix, ignoreCase, block)

fun date(
        format: String,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.date(format, block)

fun textLength(
        min: Long,
        max: Long,
        block: (ValidatableModel<String>.() -> Unit)? = null
): ValidatableModel<String> = ModelFactory.textLength(min, max, block)

fun floatRange(
        min: Float,
        max: Float,
        block: (ValidatableModel<Float>.() -> Unit)? = null
): ValidatableModel<Float> = ModelFactory.floatRange(min, max, block)

fun doubleRange(
        min: Double,
        max: Double,
        block: (ValidatableModel<Double>.() -> Unit)? = null
): ValidatableModel<Double> = ModelFactory.doubleRange(min, max, block)

fun longRange(
        min: Long,
        max: Long,
        block: (ValidatableModel<Long>.() -> Unit)? = null
): ValidatableModel<Long> = ModelFactory.longRange(min, max, block)

fun intRange(
        min: Int,
        max: Int,
        block: (ValidatableModel<Int>.() -> Unit)? = null
): ValidatableModel<Int> = ModelFactory.intRange(min, max, block)

fun charRange(
        min: Char,
        max: Char,
        block: (ValidatableModel<Char>.() -> Unit)? = null
): ValidatableModel<Char> = ModelFactory.charRange(min, max, block)

fun shortRange(
        min: Short,
        max: Short,
        block: (ValidatableModel<Short>.() -> Unit)? = null
): ValidatableModel<Short> = ModelFactory.shortRange(min, max, block)

fun byteRange(
        min: Byte,
        max: Byte,
        block: (ValidatableModel<Byte>.() -> Unit)? = null
): ValidatableModel<Byte> = ModelFactory.byteRange(min, max, block)

fun boolean(
        validation: Boolean,
        block: (ValidatableModel<Boolean>.() -> Unit)? = null
): ValidatableModel<Boolean> = ModelFactory.boolean(validation, block)