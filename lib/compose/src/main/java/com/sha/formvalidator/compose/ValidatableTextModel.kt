package com.sha.formvalidator.compose

import androidx.compose.State
import androidx.compose.state
import androidx.compose.unaryPlus
import com.sha.formvalidator.core.DefaultErrors
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.composite.AndValidator
import com.sha.formvalidator.core.validator.composite.OrValidator
import com.sha.formvalidator.core.validator.pattern.*
import java.util.regex.Pattern

interface Recomposable {
    var recompose: () -> Unit
}

interface ValidatableModel: Recomposable {
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

interface ValidatableTextModel: ValidatableModel {
    var text: String
}

abstract class AbstractValidatableTextModel: ValidatableTextModel {
    override var text: String = ""
        set(value) {
            field = value
            isValid = RequiredValidator(value).isValid(value)
        }
    abstract val validator: TextValidator
    override var isValid: Boolean = false
    override var validateOnChange: Boolean = false
    override var forceValidationOnce: Boolean = false
    override var recompose: () -> Unit = {}
}

fun <T: ValidatableTextModel> State<T>.text() = value.text
fun <T: ValidatableModel> State<T>.errorText() = value.errorText
fun <T: ValidatableModel> State<T>.isValid() = value.isValid
fun <T: ValidatableModel> State<T>.validateOnChange() = value.validateOnChange
fun <T: ValidatableModel> State<T>.validate() = value.validate()

class CompositeValidation<T: ValidatableModel> {
    internal var list: MutableList<State<T>> = mutableListOf()

    fun  add(model: State<T>) {
        list.add(model)
    }

    fun addAll(models: List<State<T>>) {
        list.addAll(models)
    }

    fun remove(model: State<T>) {
        list.remove(model)
    }

    fun removeAll(models: List<State<T>>) {
        list.removeAll(models)
    }

    val isValid: Boolean
        get() = ComposeValidator(this).isValid
}

@Suppress("UNCHECKED_CAST")
object Validation {

    fun  mandatory(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: MandatoryValidation.() -> Unit
    ): State<MandatoryValidation> {
        return MandatoryValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun optional(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: OptionalValidation.() -> Unit
    ): State<OptionalValidation> {
        return OptionalValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun webUrl(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: WebUrlValidation.() -> Unit
    ): State<WebUrlValidation> {
        return WebUrlValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun phone(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: PhoneValidation.() -> Unit
    ): State<PhoneValidation> {
        return PhoneValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun personName(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: PersonNameValidation.() -> Unit
    ): State<PersonNameValidation> {
        return PersonNameValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun personFullName(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: PersonFullNameValidation.() -> Unit
    ): State<PersonFullNameValidation> {
        return PersonFullNameValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun ipAddress(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: IpAddressValidation.() -> Unit
    ): State<IpAddressValidation> {
        return IpAddressValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun email(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: EmailValidation.() -> Unit
    ): State<EmailValidation> {
        return EmailValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun domain(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: DomainValidatorValidation.() -> Unit
    ): State<DomainValidatorValidation> {
        return DomainValidatorValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun alpha(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: AlphaValidation.() -> Unit
    ): State<AlphaValidation> {
        return AlphaValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun alphaNumeric(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: AlphaNumericValidation.() -> Unit
    ): State<AlphaNumericValidation> {
        return AlphaNumericValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun valueMatch(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: ValueMatchValidation.() -> Unit
    ): State<ValueMatchValidation> {
        return ValueMatchValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun numeric(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: NumericValidation.() -> Unit
    ): State<NumericValidation> {
        return NumericValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun creditCard(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: CreditCardValidation.() -> Unit
    ): State<CreditCardValidation> {
        return CreditCardValidation.create(block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun pattern(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            pattern: Pattern, block: PatternValidation.() -> Unit
    ): State<PatternValidation> {
        return PatternValidation.create(pattern, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun or(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: OrValidation.() -> Unit,
            vararg validators: TextValidator
    ): State<OrValidation> {
        return OrValidation.create(block, *validators).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun and(
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: AndValidation.() -> Unit,
            vararg validators: TextValidator
    ): State<AndValidation> {
        return AndValidation.create(block, *validators).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun prefix(
            prefix: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: PrefixValidation.() -> Unit
    ): State<PrefixValidation> {
        return PrefixValidation.create(prefix, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun suffix(
            suffix: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: SuffixValidation.() -> Unit
    ): State<SuffixValidation> {
        return SuffixValidation.create(suffix, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun date(
            format: String,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: DateValidation.() -> Unit
    ): State<DateValidation> {
        return DateValidation.create(format, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun numericRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: NumericRangeValidation.() -> Unit
    ): State<NumericRangeValidation> {
        return NumericRangeValidation.create(min, max, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun lengthRange(
            min: Long,
            max: Long,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: LengthRangeValidation.() -> Unit
    ): State<LengthRangeValidation> {
        return LengthRangeValidation.create(min, max, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }

    fun floatNumericRange(
            min: Double,
            max: Double,
            compositeValidation: CompositeValidation<ValidatableModel>? = null,
            block: FloatNumericRangeValidation.() -> Unit
    ): State<FloatNumericRangeValidation> {
        return FloatNumericRangeValidation.create(min, max, block).apply {
            compositeValidation?.add(this as State<ValidatableModel>)
        }
    }
}


class MandatoryValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { RequiredValidator(errorText) }
    override var errorText = DefaultErrors.mandatoryError

    companion object {
        fun create(block: MandatoryValidation.() -> Unit) = +state { MandatoryValidation().apply(block) }
    }
}

class OptionalValidation private constructor(): AbstractValidatableTextModel() {
    override val validator = object : TextValidator() { override fun isValid(text: String) = true }
    override var errorText: String = ""

    companion object {
        fun create(block: OptionalValidation.() -> Unit) = +state { OptionalValidation().apply(block) }
    }
}

class WebUrlValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { WebUrlValidator(errorText) }
    override var errorText = DefaultErrors.webUrlError

    companion object {
        fun create(block: WebUrlValidation.() -> Unit) = +state { WebUrlValidation().apply(block) }
    }
}

class PhoneValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PhoneValidator(errorText) }
    override var errorText = DefaultErrors.phoneError

    companion object {
        fun create(block: PhoneValidation.() -> Unit) = +state { PhoneValidation().apply(block) }
    }
}

class PersonNameValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonNameValidator(errorText) }
    override var errorText = DefaultErrors.personNameError

    companion object {
        fun create(block: PersonNameValidation.() -> Unit) = +state { PersonNameValidation().apply(block) }
    }
}

class PersonFullNameValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PersonFullNameValidator(errorText) }
    override var errorText = DefaultErrors.personFullNameError

    companion object {
        fun create(block: PersonFullNameValidation.() -> Unit) = +state { PersonFullNameValidation().apply(block) }
    }
}

class PatternValidation private constructor(pattern: Pattern): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PatternValidator(errorText, pattern) }
    override var errorText = DefaultErrors.patternError

    companion object {
        fun create(pattern: Pattern, block: PatternValidation.() -> Unit): State<PatternValidation> {
            return +state { PatternValidation(pattern).apply(block) }
        }
    }
}

class IpAddressValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { IpAddressValidator(errorText) }
    override var errorText = DefaultErrors.ipAddressError

    companion object {
        fun create(block: IpAddressValidation.() -> Unit) = +state { IpAddressValidation().apply(block) }
    }
}

class EmailValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { EmailValidator(errorText) }
    override var errorText = DefaultErrors.emailError

    companion object {
        fun create(block: EmailValidation.() -> Unit) = +state { EmailValidation().apply(block) }
    }
}

class DomainValidatorValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DomainValidator(errorText) }
    override var errorText = DefaultErrors.domainError

    companion object {
        fun create(block: DomainValidatorValidation.() -> Unit) = +state { DomainValidatorValidation().apply(block) }
    }
}

class AlphaValidation private constructor(): AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaValidator(errorText) }
    override var errorText = DefaultErrors.alphaError

    companion object {
        fun create(block: AlphaValidation.() -> Unit) = +state { AlphaValidation().apply(block) }
    }
}

class AlphaNumericValidation private constructor() : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AlphaNumericValidator(errorText) }
    override var errorText = DefaultErrors.alphaNumericError

    companion object {
        fun create(block: AlphaNumericValidation.() -> Unit) = +state { AlphaNumericValidation().apply(block) }
    }
}

class OrValidation private constructor(vararg validators: TextValidator) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { OrValidator(errorText, *validators) }
    override var errorText = DefaultErrors.orError

    companion object {
        fun create(block: OrValidation.() -> Unit, vararg validators: TextValidator): State<OrValidation> {
            return +state { OrValidation(*validators).apply(block) }
        }
    }
}

class AndValidation private constructor(vararg validators: TextValidator) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { AndValidator(*validators) }
    override var errorText = ""

    companion object {
        fun create(block: AndValidation.() -> Unit, vararg validators: TextValidator) = +state { AndValidation(*validators).apply(block) }
    }
}

class ValueMatchValidation private constructor() : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { ValueMatchValidator(errorText) }
    override var errorText = DefaultErrors.valueMatchError

    companion object {
        fun create(block: ValueMatchValidation.() -> Unit) = +state { ValueMatchValidation().apply(block) }
    }
}

class PrefixValidation private constructor(prefix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { PrefixValidator(prefix, errorText) }
    override var errorText = DefaultErrors.suffixError

    companion object {
        fun create(prefix: String, block: PrefixValidation.() -> Unit) = +state { PrefixValidation(prefix).apply(block) }
    }
}

class SuffixValidation private constructor(suffix: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { SuffixValidator(suffix, errorText) }
    override var errorText = DefaultErrors.suffixError

    companion object {
        fun create(suffix: String, block: SuffixValidation.() -> Unit) = +state { SuffixValidation(suffix).apply(block) }
    }
}

class NumericValidation private constructor() : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericValidator(errorText) }
    override var errorText = DefaultErrors.numericError

    companion object {
        fun create(block: NumericValidation.() -> Unit) = +state { NumericValidation().apply(block) }
    }
}

class NumericRangeValidation private constructor(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { NumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.numericRangeError

    companion object {
        fun create(min: Long, max: Long, block: NumericRangeValidation.() -> Unit) = +state { NumericRangeValidation(min, max).apply(block) }
    }
}

class LengthRangeValidation private constructor(min: Long, max: Long) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { LengthRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.lengthRangeError

    companion object {
        fun create(min: Long, max: Long, block: LengthRangeValidation.() -> Unit) = +state { LengthRangeValidation(min, max).apply(block) }
    }
}

class FloatNumericRangeValidation private constructor(min: Double, max: Double) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { FloatNumericRangeValidator(errorText, min, max) }
    override var errorText = DefaultErrors.floatNumericRangeError

    companion object {
        fun create(min: Double, max: Double, block: FloatNumericRangeValidation.() -> Unit): State<FloatNumericRangeValidation> {
            return +state { FloatNumericRangeValidation(min, max).apply(block) }
        }
    }
}

class DateValidation private constructor(format: String) : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { DateValidator(errorText, format) }
    override var errorText = DefaultErrors.dateError

    companion object {
        fun create(format: String, block: DateValidation.() -> Unit) = +state { DateValidation(format).apply(block) }
    }
}

class CreditCardValidation private constructor() : AbstractValidatableTextModel() {
    override val validator: TextValidator by lazy { CreditCardValidator(errorText) }
    override var errorText = DefaultErrors.creditCardError

    companion object {
        fun create(block: CreditCardValidation.() -> Unit) = +state { CreditCardValidation().apply(block) }
    }
}
