package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalMinValidator extends MinValidator implements Conditional {

    private Condition condition;

    public ConditionalMinValidator(EditText et, int min, Condition condition) {
        super(et, min);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
