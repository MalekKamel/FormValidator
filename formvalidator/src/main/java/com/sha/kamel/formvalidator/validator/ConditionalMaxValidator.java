package com.sha.kamel.formvalidator.validator;

import android.widget.TextView;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalMaxValidator extends MaxValidator implements Conditional {

    private Condition condition;

    /**
     * @param tv {@link TextView} that will be validated
     * @param max the maximum length
     * @param condition if this functions returned true, the filed will be
     *                  validated
     */
    public ConditionalMaxValidator(TextView tv, int max, Condition condition) {
        super(tv, max);
        this.condition = condition;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
