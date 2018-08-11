package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalEmailValidator extends EmailValidator implements Conditional {
    private Condition condition;

    /**
     *
     * @param tv {@link TextView} that will be validated
     * @param condition if this functions returned true, the filed will be
     *                  validated
     */
    public ConditionalEmailValidator(TextView tv, Condition condition) {
        super(tv);
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
