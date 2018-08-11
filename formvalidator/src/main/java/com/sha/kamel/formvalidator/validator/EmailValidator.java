package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sha on 11/8/17.
 */

public class EmailValidator extends Validator {
    /**
     * Regex to validate e-mail.
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * @param tv {@link TextView} that will be validated
     */
    public EmailValidator(TextView tv) {
        super(tv);
    }

    @Override
    protected boolean validate(String text) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(text);
        boolean  isValid = matcher.find();
        if (!isValid)
            errorMessage = "Email is invalid.";
        return isValid;
    }

}
