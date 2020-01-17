package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.TextValidator
import java.util.regex.Pattern

object Validation {

    fun mandatory(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (MandatoryValidation.() -> Unit)? = null
    ) = makeModel(MandatoryValidation(), compositeValidation, block)

    fun optional(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (OptionalValidation.() -> Unit)? = null
    ) = makeModel(OptionalValidation(), compositeValidation, block)

    fun webUrl(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (WebUrlValidation.() -> Unit)? = null
    ) = makeModel(WebUrlValidation(), compositeValidation, block)

    fun phone(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PhoneValidation.() -> Unit)? = null
    ) = makeModel(PhoneValidation(), compositeValidation, block)

    fun personName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PersonNameValidation.() -> Unit)? = null
    ) = makeModel(PersonNameValidation(), compositeValidation, block)

    fun personFullName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PersonFullNameValidation.() -> Unit)? = null
    ) = makeModel(PersonFullNameValidation(), compositeValidation, block)

    fun ipAddress(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (IpAddressValidation.() -> Unit)? = null
    ) = makeModel(IpAddressValidation(), compositeValidation, block)

    fun email(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (EmailValidation.() -> Unit)? = null
    ) = makeModel(EmailValidation(), compositeValidation, block)

    fun domain(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DomainValidatorValidation.() -> Unit)? = null
    ) = makeModel(DomainValidatorValidation(), compositeValidation, block)

    fun alpha(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (AlphaValidation.() -> Unit)? = null
    ) = makeModel(AlphaValidation(), compositeValidation, block)

    fun alphaNumeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (AlphaNumericValidation.() -> Unit)? = null
    ) = makeModel(AlphaNumericValidation(), compositeValidation, block)

    fun valueMatch(
            vararg models: ValidatableModel<String>,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValueMatchValidation.() -> Unit)? = null
    ) = makeModel(ValueMatchValidation(*models), compositeValidation, block)

    fun numeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (NumericValidation.() -> Unit)? = null
    ) = makeModel(NumericValidation(), compositeValidation, block)

    fun creditCard(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (CreditCardValidation.() -> Unit)? = null
    ) = makeModel(CreditCardValidation(), compositeValidation, block)

    fun pattern(
            pattern: Pattern,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PatternValidation.() -> Unit)? = null
    ) = makeModel(PatternValidation(pattern), compositeValidation, block)

    fun or(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (OrValidation.() -> Unit)? = null
    ) = makeModel(OrValidation(validators), compositeValidation, block)

    fun and(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (AndValidation.() -> Unit)? = null
    ) = makeModel(AndValidation(validators), compositeValidation, block)

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PrefixValidation.() -> Unit)? = null
    ) = makeModel(PrefixValidation(prefix), compositeValidation, block)

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (SuffixValidation.() -> Unit)? = null
    ) = makeModel(SuffixValidation(suffix), compositeValidation, block)

    fun date(
            format: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DateValidation.() -> Unit)? = null
    ) = makeModel(DateValidation(format), compositeValidation, block)

    fun numericRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (NumericRangeValidation.() -> Unit)? = null
    ) = makeModel(NumericRangeValidation(min, max), compositeValidation, block)

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (LengthRangeValidation.() -> Unit)? = null
    ) = makeModel(LengthRangeValidation(min, max), compositeValidation, block)

    fun floatRange(
            min: Float,
            max: Float,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (FloatRangeValidation.() -> Unit)? = null
    ) = makeModel(FloatRangeValidation(min, max), compositeValidation, block)

    fun doubleRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DoubleRangeValidation.() -> Unit)? = null
    ) = makeModel(DoubleRangeValidation(min, max), compositeValidation, block)

    fun longRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (LongRangeValidation.() -> Unit)? = null
    ) = makeModel(LongRangeValidation(min, max), compositeValidation, block)

    fun intRange(
            min: Int,
            max: Int,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (IntRangeValidation.() -> Unit)? = null
    ) = makeModel(IntRangeValidation(min, max), compositeValidation, block)
    fun charRange(
            min: Char,
            max: Char,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (CharRangeValidation.() -> Unit)? = null
    ) = makeModel(CharRangeValidation(min, max), compositeValidation, block)

    fun shortRange(
            min: Short,
            max: Short,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ShortRangeValidation.() -> Unit)? = null
    ) = makeModel(ShortRangeValidation(min, max), compositeValidation, block)

    fun byteRange(
            min: Byte,
            max: Byte,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ByteRangeValidation.() -> Unit)? = null
    ) = makeModel(ByteRangeValidation(min, max), compositeValidation, block)

    fun boolean(
            validation: Boolean,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (BooleanValidation.() -> Unit)? = null
    ) = makeModel(BooleanValidation(validation), compositeValidation, block)

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