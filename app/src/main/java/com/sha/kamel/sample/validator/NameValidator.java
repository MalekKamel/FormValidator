package com.sha.kamel.sample.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;
import com.sha.kamel.sample.util.MyApp;
import com.sha.kamel.sample.R;

/**
 * Created by Sha on 11/8/17.
 */

public class NameValidator extends Validator{

    public NameValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        int length = text.length();
        if (length < 4) {
            error(MyApp.getContext().getString(R.string.name_min_length_4));
            return false;
        }
        if (length > 100) {
            error(MyApp.getContext().getString(R.string.name_max_length_100));
            return false;
        }
        return true;
    }
}
