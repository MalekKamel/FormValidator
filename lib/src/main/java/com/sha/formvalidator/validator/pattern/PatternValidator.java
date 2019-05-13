package com.sha.formvalidator.validator.pattern;

import android.widget.EditText;

import com.sha.formvalidator.validator.Validator;

import java.util.regex.Pattern;

/**
 * Base class for regexp based validators.
 *
 * @see DomainValidator
 * @see EmailValidator
 * @see IpAddressValidator
 * @see PhoneValidator
 * @see WebUrlValidator
 */
public class PatternValidator extends Validator {
    private Pattern pattern;

    public PatternValidator(String errorMessage, String regex) {
        this(errorMessage, Pattern.compile(regex));
    }

    public PatternValidator(String _customErrorMessage, Pattern pattern) {
        super(_customErrorMessage);
        if (pattern == null) throw new IllegalArgumentException("pattern must not be null");
        this.pattern = pattern;
    }

    public boolean isValid(EditText et) {
        return pattern.matcher(et.getText()).matches();
    }

}
