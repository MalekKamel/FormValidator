package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalMinValidator extends MinValidator implements Conditional {

    private Condition condition;

    /**
     * @param tv {@link TextView} that will be validated
     * @param min the minimum length
     * @param condition if this functions returned true, the filed will be
     *                  validated
     */
    public ConditionalMinValidator(TextView tv, int min, Condition condition) {
        super(tv, min);
        this.condition = condition;
    }

    /**
     * Function used internally to get the condition
     * @return condition
     */
    @Override
    public Condition condition() {
        return condition;
    }
}
