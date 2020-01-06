package com.sha.formvalidator.compose

import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

interface ValidatableModel: ValidatableTextModel, Recomposable {
    var errorText: String
    var isValid: Boolean
    var validateOnChange: Boolean
    var forceValidationOnce: Boolean

    fun validate(): Boolean {
        this.forceValidationOnce = true
        recompose()
        return isValid
    }
}

interface ValidatableTextModel {
    var text: String
}

interface Recomposable {
    var recompose: () -> Unit
}

abstract class AbstractValidatableTextModel: ValidatableModel {
    override var text: String = ""
        set(value) {
            field = value
            isValid = validator.isValid(value)
        }
    abstract val validator: TextValidator
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var forceValidationOnce: Boolean = false
    override var recompose: () -> Unit = {}
}

class CompositeValidation<T: ValidatableModel> {
    internal var list: MutableList<T> = mutableListOf()
    val isValid: Boolean = ComposeValidator(this).isValid
    fun  add(model: T) = list.add(model)
    fun addAll(models: List<T>) = list.addAll(models)
    fun remove(model: T)  = list.remove(model)
    fun removeAll(models: List<T>) = list.removeAll(models)
}

@Suppress("UNCHECKED_CAST")
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


class MandatoryValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { RequiredValidator(errorText) }
    override var errorText = DefaultErrors.mandatoryError
}

class OptionalValidation: AbstractValidatableTextModel() {
    override val validator = object : TextValidator() { override fun isValid(text: String) = true }
    override var errorText: String = ""
}

class WebUrlValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { WebUrlValidator(errorText) }
    override var errorText = DefaultErrors.webUrlError
}

class PhoneValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PhoneValidator(errorText) }
    override var errorText = DefaultErrors.phoneError
}

class PersonNameValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonNameValidator(errorText) }
    override var errorText = DefaultErrors.personNameError
}

class PersonFullNameValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator(errorText) }
    override var errorText = DefaultErrors.personFullNameError
}

class PatternValidation(pattern: Pattern): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PatternValidator(errorText, pattern) }
    override var errorText = DefaultErrors.patternError
}

class IpAddressValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { IpAddressValidator(errorText) }
    override var errorText = DefaultErrors.ipAddressError
}

class EmailValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { EmailValidator(errorText) }
    override var errorText = DefaultErrors.emailError
}

class DomainValidatorValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DomainValidator(errorText) }
    override var errorText = DefaultErrors.domainError
}

class AlphaValidation: AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaValidator(errorText) }
    override var errorText = DefaultErrors.alphaError
}

class AlphaNumericValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator(errorText) }
    override var errorText = DefaultErrors.alphaNumericError
}

class OrValidation(validators: List<TextValidator>) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { OrValidator(errorText, validators) }
    override var errorText = DefaultErrors.orError
}

class AndValidation(validators: List<TextValidator>) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AndValidator(validators) }
    override var errorText = ""
}

class ValueMatchValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { ValueMatchValidator(errorText) }
    override var errorText = DefaultErrors.valueMatchError
}

class PrefixValidation(prefix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class SuffixValidation(suffix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix, errorText) }
    override var errorText = DefaultErrors.suffixError
}

class NumericValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericValidator(errorText) }
    override var errorText = DefaultErrors.numericError
}

class NumericRangeValidation(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.numericRangeError
}

class LengthRangeValidation(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { LengthRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.lengthRangeError
}

class FloatNumericRangeValidation(min: Double, max: Double) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { FloatNumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.floatNumericRangeError
}

class DateValidation(format: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DateValidator(errorText, format) }
    override var errorText = DefaultErrors.dateError
}

class CreditCardValidation : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { CreditCardValidator(errorText) }
    override var errorText = DefaultErrors.creditCardError
}
