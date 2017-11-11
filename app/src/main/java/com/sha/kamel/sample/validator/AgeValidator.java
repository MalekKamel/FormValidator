package com.sha.kamel.sample.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;
import com.sha.kamel.sample.util.MyApp;
import com.sha.kamel.sample.R;

/**
 * Created by Sha on 11/8/17.
 */

public class AgeValidator extends Validator{

    public AgeValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        if (text.length() != 2){
            et.setError(MyApp.getContext().getString(R.string.age_invalid));
            return false;
        }
        return true;
    }
}
