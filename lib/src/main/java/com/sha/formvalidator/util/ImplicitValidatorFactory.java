package com.sha.formvalidator.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.sha.formvalidator.R;
import com.sha.formvalidator.model.Options;
import com.sha.formvalidator.validator.CreditCardValidator;
import com.sha.formvalidator.validator.CustomValidator;
import com.sha.formvalidator.validator.DateValidator;
import com.sha.formvalidator.validator.DummyValidator;
import com.sha.formvalidator.validator.InverseValidator;
import com.sha.formvalidator.validator.RequiredValidator;
import com.sha.formvalidator.validator.FloatNumericRangeValidator;
import com.sha.formvalidator.validator.NumericRangeValidator;
import com.sha.formvalidator.validator.NumericValidator;
import com.sha.formvalidator.validator.Validator;
import com.sha.formvalidator.validator.composite.AndValidator;
import com.sha.formvalidator.validator.pattern.AlphaNumericValidator;
import com.sha.formvalidator.validator.pattern.AlphaValidator;
import com.sha.formvalidator.validator.pattern.DomainValidator;
import com.sha.formvalidator.validator.pattern.EmailValidator;
import com.sha.formvalidator.validator.pattern.IpAddressValidator;
import com.sha.formvalidator.validator.pattern.PatternValidator;
import com.sha.formvalidator.validator.pattern.PersonFullNameValidator;
import com.sha.formvalidator.validator.pattern.PersonNameValidator;
import com.sha.formvalidator.validator.pattern.PhoneValidator;
import com.sha.formvalidator.validator.pattern.WebUrlValidator;

public class ImplicitValidatorFactory {

    public static Validator validator(
            Options options,
            Context context
    ){
        Validator validator;

        switch (options.validationType){
            case NOT_DETECTABLE:

                if (!TextUtils.isEmpty(options.customValidationType)){
                    validator = customValidator(options, context);
                    break;
                }

                validator = predefinedValidator(options, context);

                break;

            default:
                validator = predefinedValidator(options, context);
                break;
        }

        if (!TextUtils.isEmpty(options.errorMessage)) validator.setErrorMessage(options.errorMessage);

        // If the xml tells us that this is not a required field, we will add new InverseValidator(new RequiredValidator()).
        if (options.required)
            return ValidatorFactory.allValid(
                    new RequiredValidator(options.emptyErrorMessage(context)),
                    validator
            );

        return ValidatorFactory.anyValid(
                validator.getErrorMessage(),
                new InverseValidator(new RequiredValidator()),
                validator
        );
    }

    private static Validator customValidator(Options options, Context context) {
        Optional<CustomValidator> opt = Stream.of(Validators.customValidators)
                .filter(item -> item.customValidationType(context).equals(options.customValidationType))
                .findFirst();

        if (!opt.isPresent())
            throw new IllegalStateException(
                    "couldn't find a custom validator for custom validation type: "
                            + options.customValidationType
            );

        return opt.get();
    }

    @SuppressLint("StringFormatMatches")
    private static Validator predefinedValidator(Options options, Context context) {
        Validator validator;

        switch (options.validationType) {

            default:
            case NOT_EMPTY:
                validator = new DummyValidator();
                break;

            case ALPHA:
                validator = new AlphaValidator(context.getString(R.string.error_only_standard_letters_are_allowed));
                break;

            case ALPHA_NUMERIC:
                validator = new AlphaNumericValidator(context.getString(R.string.error_this_field_cannot_contain_special_character));
                break;

            case NUMERIC:
                validator = new NumericValidator(context.getString(R.string.error_only_numeric_digits_allowed));
                break;

            case NUMERIC_RANGE:
                validator = new NumericRangeValidator(
                        context.getString(R.string.error_only_numeric_digits_range_allowed, options.minNumber, options.maxNumber),
                        options.minNumber,
                        options.maxNumber
                );
                break;

            case FLOAT_NUMERIC_RANGE:
                validator = new FloatNumericRangeValidator(
                        context.getString(R.string.error_only_numeric_digits_range_allowed, options.floatMinNumber, options.floatMaxNumber),
                        options.floatMinNumber,
                        options.floatMaxNumber
                );
                break;

            case REGEX:
                validator = new PatternValidator(options.errorMessage, options.regex);
                break;

            case CREDIT_CARD:
                validator = new CreditCardValidator(context.getString(R.string.error_credit_card_number_not_valid));
                break;

            case EMAIL:
                validator = new EmailValidator(context.getString(R.string.error_email_address_not_valid));
                break;

            case PHONE:
                validator = new PhoneValidator(context.getString(R.string.error_phone_not_valid));
                break;

            case DOMAIN_NAME:
                validator = new DomainValidator(context.getString(R.string.error_domain_not_valid));
                break;

            case IP_ADDRESS:
                validator = new IpAddressValidator(context.getString(R.string.error_ip_not_valid));
                break;

            case WEB_URL:
                validator = new WebUrlValidator(context.getString(R.string.error_url_not_valid));
                break;

            case PERSON_NAME:
                validator = new PersonNameValidator(context.getString(R.string.error_not_valid_person_name));
                break;

            case PERSON_FULL_NAME:
                validator = new PersonFullNameValidator(context.getString(R.string.error_not_valid_person_full_name));
                break;

            case DATE:
                validator = new DateValidator(context.getString(R.string.error_date_not_valid), options.dateFormat);
                break;
        }

        return validator;
    }

}
