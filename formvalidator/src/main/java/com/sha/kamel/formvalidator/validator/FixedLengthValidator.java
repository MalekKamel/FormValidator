package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class FixedLengthValidator extends Validator {

    private int length;

    /**
     * @param tv {@link TextView} that will be validated
     * @param length the fixed length
     */
    public FixedLengthValidator(TextView tv, int length) {
        super(tv);
        this.length = length;
    }

    @Override
    protected boolean validate(String text) {
        if (text.length() != length){
            errorMessage = "Characters Must be " + length;
            return false;
        }
        return true;
    }

}
