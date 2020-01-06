package com.sha.formvalidator.compose

import com.sha.formvalidator.core.validator.TextValidator
import java.util.regex.Pattern

object Validation {

    fun  mandatory(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (MandatoryValidation.() -> Unit)? = null
    ): ValidatableModel {
        return MandatoryValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun optional(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (OptionalValidation.() -> Unit)? = null
    ): OptionalValidation {
        return OptionalValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun webUrl(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (WebUrlValidation.() -> Unit)? = null
    ): WebUrlValidation {
        return WebUrlValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun phone(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (PhoneValidation.() -> Unit)? = null
    ): PhoneValidation {
        return PhoneValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun personName(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (PersonNameValidation.() -> Unit)? = null
    ): PersonNameValidation {
        return PersonNameValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun personFullName(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (PersonFullNameValidation.() -> Unit)? = null
    ): PersonFullNameValidation {
        return PersonFullNameValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun ipAddress(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (IpAddressValidation.() -> Unit)? = null
    ): IpAddressValidation {
        return IpAddressValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun email(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (EmailValidation.() -> Unit)? = null
    ): EmailValidation {
        return EmailValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun domain(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (DomainValidatorValidation.() -> Unit)? = null
    ): DomainValidatorValidation {
        return DomainValidatorValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun alpha(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (AlphaValidation.() -> Unit)? = null
    ): AlphaValidation {
        return AlphaValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun alphaNumeric(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (AlphaNumericValidation.() -> Unit)? = null
    ): AlphaNumericValidation {
        return AlphaNumericValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun valueMatch(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (ValueMatchValidation.() -> Unit)? = null
    ): ValueMatchValidation {
        return ValueMatchValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun numeric(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (NumericValidation.() -> Unit)? = null
    ): NumericValidation {
        return NumericValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun creditCard(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (CreditCardValidation.() -> Unit)? = null
    ): CreditCardValidation {
        return CreditCardValidation()
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun pattern(
            pattern: Pattern,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (PatternValidation.() -> Unit)? = null
    ): PatternValidation {
        return PatternValidation(pattern)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun or(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            validators: List<TextValidator>,
            block: (OrValidation.() -> Unit)? = null
    ): OrValidation {
        return OrValidation(validators)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun and(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            validators: List<TextValidator>,
            block: (AndValidation.() -> Unit)? = null
    ): AndValidation {
        return AndValidation(validators)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (PrefixValidation.() -> Unit)? = null
    ): PrefixValidation {
        return PrefixValidation(prefix)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (SuffixValidation.() -> Unit)? = null
    ): SuffixValidation {
        return SuffixValidation(suffix)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun date(
            format: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (DateValidation.() -> Unit)? = null
    ): DateValidation {
        return DateValidation(format)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun numericRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (NumericRangeValidation.() -> Unit)? = null
    ): NumericRangeValidation {
        return NumericRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (LengthRangeValidation.() -> Unit)? = null
    ): LengthRangeValidation {
        return LengthRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }

    fun floatNumericRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: (FloatNumericRangeValidation.() -> Unit)? = null
    ): FloatNumericRangeValidation {
        return FloatNumericRangeValidation(min, max)
                .apply { block?.invoke(this) }
                .apply { compositeValidation?.add(this) }
    }
}