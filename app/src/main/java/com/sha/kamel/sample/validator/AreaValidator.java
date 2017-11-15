package com.sha.kamel.sample.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Validator;
import com.sha.kamel.sample.util.MyApp;
import com.sha.kamel.sample.R;

/**
 * Created by Sha on 11/8/17.
 */

public class AreaValidator extends Validator{

    public AreaValidator(EditText et) {
        super(et);
    }

    @Override
    protected boolean validate(String text) {
        boolean isValid = text.length() <= 25;
        if (!isValid)
            error(MyApp.getContext().getString(R.string.area_max_length_25));
        return isValid;
    }
}
