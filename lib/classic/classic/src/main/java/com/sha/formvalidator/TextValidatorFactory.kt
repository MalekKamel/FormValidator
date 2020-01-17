package com.sha.formvalidator

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.*
import com.sha.formvalidator.core.validator.pattern.*

object TextValidatorFactory {

    fun validator(
            attrInfo: TextViewAttrInfo,
            context: Context
    ): Validator<String> {
        val validator = when (attrInfo.validationType) {
            TextViewValidationType.NOT_DETECTABLE -> {
                if (attrInfo.customValidationType.isNotEmpty())
                    customValidator(attrInfo, context) else
                    predefinedValidator(attrInfo, context)
            }
            else -> predefinedValidator(attrInfo, context)
        }

        if (!TextUtils.isEmpty(attrInfo.errorMessage)) validator.errorMessage = attrInfo.errorMessage

        // If the xml tells us that this is not a required field, we will add InverseValidator(RequiredValidator()).
        return if (attrInfo.required) ValidatorFactory.allValid(
                MandatoryValidator().apply { errorMessage = attrInfo.emptyErrorMessage(context) },
                validator)
        else
            ValidatorFactory.anyValid(
                    InverseValidator(MandatoryValidator()),
                    validator
            ).apply { errorMessage = validator.errorMessage }
    }

    private fun customValidator(attrInfo: TextViewAttrInfo, context: Context): TextValidator {
        val opt = TextViewValidators.customValidators.firstOrNull {
            it.customValidationType(context) == attrInfo.customValidationType
        }
        check(opt != null) { "couldn't find a custom validator for custom validation type: ${attrInfo.customValidationType}" }
        return opt
    }

    @SuppressLint("StringFormatMatches")
    private fun predefinedValidator(attrInfo: TextViewAttrInfo, context: Context): Validator<String> {
        return when (attrInfo.validationType) {
            TextViewValidationType.NOT_EMPTY -> DummyValidator()
            TextViewValidationType.ALPHA -> AlphaValidator().apply { errorMessage = context.getString(R.string.error_only_standard_letters_are_allowed) }
            TextViewValidationType.ALPHA_NUMERIC -> AlphaNumericValidator().apply { errorMessage = context.getString(R.string.error_this_field_cannot_contain_special_character) }
            TextViewValidationType.NUMERIC -> NumericValidator().apply { errorMessage = context.getString(R.string.error_only_numeric_digits_allowed) }
            TextViewValidationType.REGEX -> PatternValidator( attrInfo.regex).apply { errorMessage = attrInfo.errorMessage }
            TextViewValidationType.CREDIT_CARD -> CreditCardValidator().apply { errorMessage = context.getString(R.string.error_credit_card_number_not_valid) }
            TextViewValidationType.EMAIL -> EmailValidator().apply { errorMessage = context.getString(R.string.error_email_address_not_valid) }
            TextViewValidationType.PHONE -> PhoneValidator().apply { errorMessage = context.getString(R.string.error_phone_not_valid) }
            TextViewValidationType.DOMAIN_NAME -> DomainValidator().apply { errorMessage = context.getString(R.string.error_domain_not_valid) }
            TextViewValidationType.IP_ADDRESS -> IpAddressValidator().apply { errorMessage = context.getString(R.string.error_ip_not_valid) }
            TextViewValidationType.WEB_URL -> WebUrlValidator().apply { errorMessage = context.getString(R.string.error_url_not_valid) }
            TextViewValidationType.PERSON_NAME -> PersonNameValidator().apply { errorMessage = context.getString(R.string.error_not_valid_person_name) }
            TextViewValidationType.PERSON_FULL_NAME -> PersonFullNameValidator().apply { errorMessage = context.getString(R.string.error_not_valid_person_full_name) }
            TextViewValidationType.DATE -> DateValidator(attrInfo.dateFormat).apply { errorMessage = context.getString(R.string.error_date_not_valid) }

            TextViewValidationType.NUMERIC_RANGE -> {
                val validator = LongRangeValidator(
                        attrInfo.minNumber,
                        attrInfo.maxNumber
                ).apply { errorMessage = context.getString(R.string.error_only_numeric_digits_range_allowed, attrInfo.minNumber, attrInfo.maxNumber) }

                WrapValidator(validator) { it?.toLong() }
            }
            TextViewValidationType.FLOAT_NUMERIC_RANGE -> {
                val validator = FloatRangeValidator(
                        attrInfo.floatMinNumber,
                        attrInfo.floatMaxNumber
                ).apply { errorMessage = context.getString(R.string.error_only_numeric_digits_range_allowed, attrInfo.floatMinNumber, attrInfo.floatMaxNumber) }

                WrapValidator(validator) { it?.toFloat() }
            }
            else -> DummyValidator()
        }
    }

}
