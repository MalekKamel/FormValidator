package com.sha.kamel.sample.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Validator;
import com.sha.kamel.sample.R;
import com.sha.kamel.sample.util.MyApp;

/**
 * Created by Sha on 11/8/17.
 */

public class MobileValidator extends Validator{

    public MobileValidator(TextView tv) {
        super(tv);
    }

    @Override
    protected boolean validate(String text) {

        boolean is11 = text.length() == 11;
        boolean is01 = text.startsWith("01");

        if (!is01){
            errorMessage = MyApp.getContext().getString(R.string.phone_must_be_01);
            return false;
        }

        if (!is11){
            errorMessage = MyApp.getContext().getString(R.string.phone_must_be_11);
            return false;
        }

        return true;
    }
}
