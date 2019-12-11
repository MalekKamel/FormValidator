package com.sha.formvalidatorsample.validator;

import android.content.Context;
import android.widget.EditText;

import com.sha.formvalidator.validator.CustomValidator;
import com.sha.formvalidatorsample.R;

public class NumberOneCustomValidator extends CustomValidator {

    public NumberOneCustomValidator(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String customValidationType(Context context) {
        return context.getString(R.string.custom_validator_number_one);
    }

    @Override
    public boolean isValid(EditText et) {
        return et.getText().toString().equals("1");
    }

}
