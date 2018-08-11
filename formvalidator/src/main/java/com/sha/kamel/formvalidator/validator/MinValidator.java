package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class MinValidator extends Validator {

    private int min;
    private String minError = "Characters can't be greater than";

    /**
     * @param tv {@link TextView} that will be validated
     * @param min the minimum length

     */
    public MinValidator(TextView tv, int min) {
        super(tv);
        this.min = min;
    }

    @Override
    protected boolean validate(String text) {
        int length = text.length();
        
        if (length < min) {
            errorMessage = minError + " " + min;
            return false;
        }

        return true;
    }

    /**
     * Call this method to provide a message for minimum error
     * @param msg message
     * @return this
     */
    public MinValidator minErrorMessage(String msg){
        minError = msg;
        return this;
    }

}
