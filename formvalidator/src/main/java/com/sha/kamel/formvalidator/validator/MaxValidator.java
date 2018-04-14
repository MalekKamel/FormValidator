package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;

/**
 * Created by Sha on 11/8/17.
 */

public class MaxValidator extends Validator {

    private int max;
    private String maxError = "Characters can't be greater than";

    public MaxValidator(EditText et, int max) {
        super(et);
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

    public MaxValidator maxErrorMessage(String msg){
        maxError = msg;
        return this;
    }

}
