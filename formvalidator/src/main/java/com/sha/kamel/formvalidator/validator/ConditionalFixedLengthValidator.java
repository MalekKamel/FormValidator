package com.sha.kamel.formvalidator.validator;


import android.widget.TextView;

import com.sha.kamel.formvalidator.Conditional;
import com.sha.kamel.formvalidator.util.Condition;

/**
 * Created by Sha on 11/8/17.
 */

public class ConditionalFixedLengthValidator extends FixedLengthValidator implements Conditional {

    private Condition condition;

    /**
     * @param tv {@link TextView} that will be validated
     * @param length the fixed length
     * @param condition if this functions returned true, the filed will be
     *                  validated
     */
    public ConditionalFixedLengthValidator(TextView tv, int length, Condition condition) {
        super(tv, length);
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
