package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class MaxValidator extends Validator {

    private int max;
    private String maxError = "Characters can't be greater than";

    /**
     * @param tv {@link TextView} that will be validated
     * @param max the maximum length
     */
    public MaxValidator(TextView tv, int max) {
        super(tv);
        this.max = max;
    }

    @Override
    protected boolean validate(String text) {
        int length = text.length();

        if (length > max) {
            errorMessage = maxError + " " + max;
            return false;
        }

        return true;
    }

    /**
     * Call this method to provide a message for maximum error
     * @param msg message
     * @return this
     */
    public MaxValidator maxErrorMessage(String msg){
        maxError = msg;
        return this;
    }

}
