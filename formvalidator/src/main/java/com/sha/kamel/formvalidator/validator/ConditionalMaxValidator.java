package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalMaxValidator extends MaxValidator implements Conditional {

    private Condition condition;

    public ConditionalMaxValidator(EditText et, int max, Condition condition) {
        super(et, max);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
