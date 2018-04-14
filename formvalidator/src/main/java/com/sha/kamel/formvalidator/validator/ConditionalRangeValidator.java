package com.sha.kamel.formvalidator.validator;

import android.widget.EditText;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalRangeValidator extends RangeValidator implements Conditional {

    private Condition condition;

    public ConditionalRangeValidator(EditText et, int min, int max, Condition condition) {
        super(et, min, max);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
