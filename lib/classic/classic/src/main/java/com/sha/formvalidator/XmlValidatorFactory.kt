package com.sha.formvalidator

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import com.sha.formvalidator.core.R
import com.sha.formvalidator.core.validator.DummyValidator
import com.sha.formvalidator.core.validator.InverseValidator
import com.sha.formvalidator.core.validator.MandatoryValidator
import com.sha.formvalidator.core.validator.Validator

object XmlValidatorFactory {

    fun make(attrInfo: TextViewAttrInfo, context: Context): Validator<String> {
        val validator = when (attrInfo.validationType) {
            XmlValidationType.UNKNOWN -> {
                if (attrInfo.customValidationType.isNotEmpty())
                    customValidator(attrInfo, context) else
                    predefinedValidator(attrInfo, context)
            }
            else -> predefinedValidator(attrInfo, context)
        }

        if (!TextUtils.isEmpty(attrInfo.errorMessage)) validator.errorGenerator.error = { attrInfo.errorMessage }

        // If the xml tells us that this is not a required field, we will add InverseValidator(RequiredValidator()).
        return if (attrInfo.required) Validators.all(
                mandatory { errorGenerator.error = { attrInfo.emptyErrorMessage(context) } },
                validator)
        else
            any(InverseValidator(MandatoryValidator()), validator) { errorGenerator = validator.errorGenerator }
    }

    private fun customValidator(attrInfo: TextViewAttrInfo, context: Context): Validator<String> {
        val opt = TextViewValidators.customValidators.firstOrNull {
            it.customValidationType(context) == attrInfo.customValidationType
        }
        check(opt != null) { "couldn't find a custom validator for custom validation type: ${attrInfo.customValidationType}" }
        return opt
    }

    @SuppressLint("StringFormatMatches")
    private fun predefinedValidator(attrInfo: TextViewAttrInfo, context: Context): Validator<String> {
        return when (attrInfo.validationType) {
            XmlValidationType.NOT_EMPTY -> optional()
            XmlValidationType.ALPHA -> alpha {
                errorMessage = context.getString(R.string.error_only_standard_letters_are_allowed)
            }
            XmlValidationType.ALPHA_NUMERIC -> alphaNumeric {
                errorMessage = context.getString(R.string.error_this_field_cannot_contain_special_character)
            }
            XmlValidationType.NUMERIC -> numeric {
                errorMessage = context.getString(R.string.error_only_numeric_digits_allowed)
            }
            XmlValidationType.REGEX -> pattern(attrInfo.regex) {
                errorMessage = attrInfo.errorMessage
            }
            XmlValidationType.CREDIT_CARD -> creditCard {
                errorMessage = context.getString(R.string.error_credit_card_number_not_valid)
            }
            XmlValidationType.EMAIL -> email {
                errorMessage = context.getString(R.string.error_email_address_not_valid)
            }
            XmlValidationType.PHONE -> phone {
                errorMessage = context.getString(R.string.error_phone_not_valid)
            }
            XmlValidationType.DOMAIN_NAME -> domain {
                errorMessage = context.getString(R.string.error_domain_not_valid)
            }
            XmlValidationType.IP_ADDRESS -> ipAddress {
                errorMessage = context.getString(R.string.error_ip_not_valid)
            }
            XmlValidationType.WEB_URL -> webUrl {
                errorMessage = context.getString(R.string.error_url_not_valid)
            }
            XmlValidationType.PERSON_NAME -> personName {
                errorMessage = context.getString(R.string.error_not_valid_person_name)
            }
            XmlValidationType.PERSON_FULL_NAME -> personFullName {
                errorMessage = context.getString(R.string.error_not_valid_person_full_name)
            }
            XmlValidationType.DATE -> date(attrInfo.dateFormat) {
                errorMessage = context.getString(R.string.error_date_not_valid)
            }
            XmlValidationType.NUMERIC_RANGE ->
                textLength(attrInfo.minNumber, attrInfo.maxNumber) {
                    errorMessage = context.getString(R.string.error_only_numeric_digits_range_allowed, attrInfo.minNumber, attrInfo.maxNumber)
                }
            XmlValidationType.FLOAT_NUMERIC_RANGE ->
                textLength(attrInfo.minNumber, attrInfo.maxNumber) {
                    errorMessage = context.getString(R.string.error_only_numeric_digits_range_allowed, attrInfo.minNumber, attrInfo.maxNumber)
                }
            else -> DummyValidator()
        }
    }

}
