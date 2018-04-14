package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalEmailValidator extends EmailValidator implements Conditional {
    private Condition condition;

    public ConditionalEmailValidator(EditText et, Condition condition) {
        super(et);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
