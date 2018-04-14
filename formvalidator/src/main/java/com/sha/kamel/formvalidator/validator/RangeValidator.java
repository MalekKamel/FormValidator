package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class RangeValidator extends Validator {

    private int min, max;
    private String
            minError = "Characters can't be lower than",
            maxError = "Characters can't be greater than";

    public RangeValidator(EditText et, int min, int max) {
        super(et);
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

    public RangeValidator minErrorMessage(String msg){
        minError = msg;
        return this;
    }

    public RangeValidator maxErrorMessage(String msg){
        maxError = msg;
        return this;
    }

}
