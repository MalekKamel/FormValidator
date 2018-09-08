package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class RequiredValidator extends Validator {

    /**
     * @param tv {@link TextView} that will be validated
     */
    public RequiredValidator(TextView tv) {
        super(tv);
        errorMessage = "Required";
    }

    @Override
    protected boolean validate(String text) {
        // Return true ? yes, we handle 'required' in base Validator.
        return true;
    }

    /**
     * Method used internally to get error message
     * @return message
     */
    @Override
    public String getErrorMessage() {
        return options.getMessageIfEmpty();
    }
}
