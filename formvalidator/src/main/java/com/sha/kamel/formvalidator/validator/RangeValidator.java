package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class RangeValidator extends Validator {

    private int min, max;
    private String
            minError = "Characters can't be lower than",
            maxError = "Characters can't be greater than";

    /**
     * @param tv {@link TextView} that will be validated
     * @param min the minimum length
     * @param max the maximum length
     */
    public RangeValidator(TextView tv, int min, int max) {
        super(tv);
        this.min = min;
        this.max = max;
    }

    @Override
    protected boolean validate(String text) {
        int length = text.length();

        if (length < min) {
            errorMessage = minError + " " + min;
            return false;
        }

        if (length > max) {
            errorMessage = maxError + " " + max;
            return false;
        }

        return true;
    }

    /**
     * Call this method to provide a message for minimum error
     * @param msg message
     * @return this
     */
    public RangeValidator minErrorMessage(String msg){
        minError = msg;
        return this;
    }

    /**
     * Call this method to provide a message for maximum error
     * @param msg message
     * @return this
     */
    public RangeValidator maxErrorMessage(String msg){
        maxError = msg;
        return this;
    }

}
