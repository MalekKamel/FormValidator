package com.sha.formvalidator

import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AllValidator
import com.sha.formvalidator.core.validator.composite.AnyValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

/**
 * Factory for creating validators
 */
object Validators {

    @JvmOverloads
    @JvmStatic
    fun <V> mandatory(
            block: (Validator<V>.() -> Unit)? = null
    ): Validator<V> = make(MandatoryValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun <V> optional(
            block: (Validator<V>.() -> Unit)? = null
    ): Validator<V> = make(DummyValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun webUrl(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(WebUrlValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun phone(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PhoneValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun personName(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PersonNameValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun personFullName(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PersonFullNameValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun ipAddress(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(IpAddressValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun email(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(EmailValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun domain(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(DomainValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun alpha(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(AlphaValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun alphaNumeric(

            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(AlphaNumericValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun numeric(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(NumericValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun creditCard(
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(CreditCardValidator(), block)

    @JvmOverloads
    @JvmStatic
    fun pattern(
            pattern: Pattern,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PatternValidator(pattern), block)

    @JvmOverloads
    @JvmStatic
    fun pattern(
            pattern: String,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PatternValidator(pattern), block)

    @JvmOverloads
    @JvmStatic
    fun <V> any(
            vararg validators: Validator<V>,
            block: (Validator<V>.() -> Unit)? = null
    ): Validator<V> = make(AnyValidator(*validators), block)

    @JvmOverloads
    @JvmStatic
    fun <V> all(
            vararg validators: Validator<V>,
            block: (Validator<V>.() -> Unit)? = null
    ): Validator<V> = make(AllValidator(*validators), block)

    @JvmOverloads
    @JvmStatic
    fun prefix(
            prefix: String,
            ignoreCase: Boolean = false,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(PrefixValidator(prefix, ignoreCase), block)

    @JvmOverloads
    @JvmStatic
    fun suffix(
            suffix: String,
            ignoreCase: Boolean = false,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(SuffixValidator(suffix, ignoreCase), block)

    @JvmOverloads
    @JvmStatic
    fun date(
            format: String,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(DateValidator(format), block)

    @JvmOverloads
    @JvmStatic
    fun textLength(
            min: Long,
            max: Long,
            block: (Validator<String>.() -> Unit)? = null
    ): Validator<String> = make(
            wrap(LongRangeValidator(min, max)) { it?.length?.toLong() },
            block
    )

    @JvmOverloads
    @JvmStatic
    fun floatRange(
            min: Float,
            max: Float,
            block: (Validator<Float>.() -> Unit)? = null
    ): Validator<Float> = make(FloatRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun doubleRange(
            min: Double,
            max: Double,
            block: (Validator<Double>.() -> Unit)? = null
    ): Validator<Double> = make(DoubleRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun longRange(
            min: Long,
            max: Long,
            block: (Validator<Long>.() -> Unit)? = null
    ): Validator<Long> = make(LongRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun intRange(
            min: Int,
            max: Int,
            block: (Validator<Int>.() -> Unit)? = null
    ): Validator<Int> = make(IntRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun charRange(
            min: Char,
            max: Char,
            block: (Validator<Char>.() -> Unit)? = null
    ): Validator<Char> = make(CharRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun shortRange(
            min: Short,
            max: Short,
            block: (Validator<Short>.() -> Unit)? = null
    ): Validator<Short> = make(ShortRangeValidator(min, max), block)

    @JvmOverloads
    @JvmStatic
    fun byteRange(
            min: Byte,
            max: Byte,
            block: (Validator<Byte>.() -> Unit)? = null
    ): Validator<Byte> = make(ByteRangeValidator(min, max), block)

    @JvmStatic
    fun condition(
            condition: () -> Boolean,
            block: (Validator<Boolean>.() -> Unit)? = null
    ): Validator<Boolean> = make(BooleanValidator(condition), block)

    @JvmOverloads
    @JvmStatic
    fun boolean(
            validation: Boolean,
            block: (Validator<Boolean>.() -> Unit)? = null
    ): Validator<Boolean> = make(BooleanValidator { validation }, block)

    @JvmStatic
    fun <FROM, TO> wrap(validator: Validator<FROM>, convertValue: (TO?) -> FROM?)
            = make(WrapValidator<FROM, TO>(validator, convertValue))

    @JvmOverloads
    @JvmStatic
    fun <V> make(
            validator: Validator<V>,
            block: (Validator<V>.() -> Unit)? = null
    ): Validator<V> {
        
        return validator.apply { block?.invoke(this) }
    }
}

fun <V> mandatory(
        block: (Validator<V>.() -> Unit)? = null
) = Validators.mandatory(block)

fun <V> optional(
        block: (Validator<V>.() -> Unit)? = null
) = Validators.optional(block)

fun webUrl(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.webUrl(block)

fun phone(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.phone(block)

fun personName(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.personName(block)

fun personFullName(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.personFullName(block)

fun ipAddress(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.ipAddress(block)

fun email(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.email(block)

fun domain(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.domain(block)

fun alpha(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.alpha(block)

fun alphaNumeric(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.alphaNumeric(block)

fun numeric(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.numeric(block)

fun creditCard(
        block: (Validator<String>.() -> Unit)? = null
) = Validators.creditCard(block)

fun pattern(
        pattern: Pattern,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.pattern(pattern, block)

fun pattern(
        pattern: String,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.pattern(pattern, block)

fun <V> any(
        vararg validators: Validator<V>,
        block: (Validator<V>.() -> Unit)? = null
): Validator<V> = Validators.any(*validators) { block?.invoke(this) }

fun <V> all(
        vararg validators: Validator<V>,
        block: (Validator<V>.() -> Unit)? = null
): Validator<V> = Validators.all(*validators)  { block?.invoke(this) }

fun prefix(
        prefix: String,
        ignoreCase: Boolean = false,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.prefix(prefix, ignoreCase, block)

fun suffix(
        suffix: String,
        ignoreCase: Boolean = false,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.suffix(suffix, ignoreCase, block)

fun date(
        format: String,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.date(format, block)

fun textLength(
        min: Long,
        max: Long,
        block: (Validator<String>.() -> Unit)? = null
) = Validators.textLength(min, max, block)

fun <FROM, TO> wrap(validator: Validator<FROM>, convertValue: (TO?) -> FROM?)
        = Validators.wrap<FROM, TO>(validator, convertValue)

fun floatRange(
        min: Float,
        max: Float,
        block: (Validator<Float>.() -> Unit)? = null
): Validator<Float> = Validators.floatRange(min, max, block)

fun doubleRange(
        min: Double,
        max: Double,
        block: (Validator<Double>.() -> Unit)? = null
): Validator<Double> = Validators.doubleRange(min, max, block)

fun longRange(
        min: Long,
        max: Long,
        block: (Validator<Long>.() -> Unit)? = null
): Validator<Long> = Validators.longRange(min, max, block)

fun intRange(
        min: Int,
        max: Int,
        block: (Validator<Int>.() -> Unit)? = null
): Validator<Int> = Validators.intRange(min, max, block)

fun charRange(
        min: Char,
        max: Char,
        block: (Validator<Char>.() -> Unit)? = null
): Validator<Char> = Validators.charRange(min, max, block)

fun shortRange(
        min: Short,
        max: Short,
        block: (Validator<Short>.() -> Unit)? = null
): Validator<Short> = Validators.shortRange(min, max, block)

fun byteRange(
        min: Byte,
        max: Byte,
        block: (Validator<Byte>.() -> Unit)? = null
): Validator<Byte> = Validators.byteRange(min, max, block)

fun condition(
        condition: () -> Boolean,
        block: (Validator<Boolean>.() -> Unit)? = null
): Validator<Boolean> = Validators.condition(condition, block)

fun boolean(
        validation: Boolean,
        block: (Validator<Boolean>.() -> Unit)? = null
): Validator<Boolean> = Validators.boolean(validation, block)
