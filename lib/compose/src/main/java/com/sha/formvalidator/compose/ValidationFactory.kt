package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.TextValidator
import java.util.regex.Pattern

object Validation {

    fun mandatory(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (MandatoryValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return MandatoryValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun optional(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (OptionalValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return OptionalValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun webUrl(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (WebUrlValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return WebUrlValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun phone(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PhoneValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return PhoneValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun personName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PersonNameValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return PersonNameValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun personFullName(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PersonFullNameValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return PersonFullNameValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun ipAddress(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (IpAddressValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return IpAddressValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun email(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (EmailValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return EmailValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun domain(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DomainValidatorValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return DomainValidatorValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun alpha(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (AlphaValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return AlphaValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun alphaNumeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (AlphaNumericValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return AlphaNumericValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun valueMatch(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (ValueMatchValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return ValueMatchValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun numeric(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (NumericValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return NumericValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun creditCard(
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (CreditCardValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return CreditCardValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun pattern(
            pattern: Pattern,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PatternValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return PatternValidation(pattern)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun or(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (OrValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return OrValidation(validators)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun and(
            compositeValidation: CompositeValidation<Validatable>? = null,
            validators: List<TextValidator>,
            block: (AndValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return AndValidation(validators)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (PrefixValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return PrefixValidation(prefix)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (SuffixValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return SuffixValidation(suffix)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun date(
            format: String,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (DateValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return DateValidation(format)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun numericRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (NumericRangeValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return NumericRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (LengthRangeValidation.() -> Unit)? = null
    ): ValidatableModel<String> {
        return LengthRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun floatNumericRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (FloatRangeValidation.() -> Unit)? = null
    ): ValidatableModel<Float> {
        return FloatRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun boolean(
            validation: Boolean,
            compositeValidation: CompositeValidation<Validatable>? = null,
            block: (BooleanValidation.() -> Unit)? = null
    ): ValidatableModel<Boolean> {
        return BooleanValidation(validation)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }
}