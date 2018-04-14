package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.Validator;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalFixedLengthValidator extends FixedLengthValidator implements Conditional {

    private Condition condition;

    public ConditionalFixedLengthValidator(EditText et, int length, Condition condition) {
        super(et, length);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }

}
