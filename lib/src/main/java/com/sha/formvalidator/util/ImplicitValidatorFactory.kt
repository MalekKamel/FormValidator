package com.sha.formvalidator.util

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.annimon.stream.Stream
import com.sha.formvalidator.R
import com.sha.formvalidator.model.Options
import com.sha.formvalidator.model.ValidationType
import com.sha.formvalidator.validator.*
import com.sha.formvalidator.validator.pattern.*

object ImplicitValidatorFactory {

    fun validator(
            options: Options,
            context: Context
    ): Validator {

        val validator = when (options.validationType) {
            ValidationType.NOT_DETECTABLE -> {
                if (!TextUtils.isEmpty(options.customValidationType)) 
                    customValidator(options, context) else
                    predefinedValidator(options, context)
            }

            else -> predefinedValidator(options, context)
        }

        if (!TextUtils.isEmpty(options.errorMessage)) validator.errorMessage = options.errorMessage

        // If the xml tells us that this is not a required field, we will add new InverseValidator(new RequiredValidator()).
        return if (options.required) ValidatorFactory.allValid(
                RequiredValidator(options.emptyErrorMessage(context)),
                validator
        ) else ValidatorFactory.anyValid(
                validator.errorMessage,
                InverseValidator(RequiredValidator()),
                validator
        )

    }

    private fun customValidator(options: Options, context: Context): Validator {
        val opt = Stream.of(Validators.customValidators)
                .filter { item -> item.customValidationType(context) == options.customValidationType }
                .findFirst()

        check(opt.isPresent) { "couldn't find a custom validator for custom validation type: ${options.customValidationType}" }

        return opt.get()
    }

    @SuppressLint("StringFormatMatches")
    private fun predefinedValidator(options: Options, context: Context): Validator {
        return when (options.validationType) {
            ValidationType.NOT_EMPTY -> DummyValidator()

            ValidationType.ALPHA -> AlphaValidator(context.getString(R.string.error_only_standard_letters_are_allowed))

            ValidationType.ALPHA_NUMERIC -> AlphaNumericValidator(context.getString(R.string.error_this_field_cannot_contain_special_character))

            ValidationType.NUMERIC -> NumericValidator(context.getString(R.string.error_only_numeric_digits_allowed))

            ValidationType.NUMERIC_RANGE -> NumericRangeValidator(
                    context.getString(R.string.error_only_numeric_digits_range_allowed, options.minNumber, options.maxNumber),
                    options.minNumber.toLong(),
                    options.maxNumber.toLong()
            )

            ValidationType.FLOAT_NUMERIC_RANGE -> FloatNumericRangeValidator(
                    context.getString(R.string.error_only_numeric_digits_range_allowed, options.floatMinNumber, options.floatMaxNumber),
                    options.floatMinNumber.toDouble(),
                    options.floatMaxNumber.toDouble()
            )

            ValidationType.REGEX -> PatternValidator(options.errorMessage, options.regex)

            ValidationType.CREDIT_CARD -> CreditCardValidator(context.getString(R.string.error_credit_card_number_not_valid))

            ValidationType.EMAIL -> EmailValidator(context.getString(R.string.error_email_address_not_valid))

            ValidationType.PHONE -> PhoneValidator(context.getString(R.string.error_phone_not_valid))

            ValidationType.DOMAIN_NAME -> DomainValidator(context.getString(R.string.error_domain_not_valid))

            ValidationType.IP_ADDRESS -> IpAddressValidator(context.getString(R.string.error_ip_not_valid))

            ValidationType.WEB_URL -> WebUrlValidator(context.getString(R.string.error_url_not_valid))

            ValidationType.PERSON_NAME -> PersonNameValidator(context.getString(R.string.error_not_valid_person_name))

            ValidationType.PERSON_FULL_NAME -> PersonFullNameValidator(context.getString(R.string.error_not_valid_person_full_name))

            ValidationType.DATE -> DateValidator(context.getString(R.string.error_date_not_valid), options.dateFormat)

            else -> DummyValidator()
        }
    }

}
