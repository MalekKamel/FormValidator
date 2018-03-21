package com.sha.kamel.formvalidator;

import android.text.TextUtils;
import android.widget.EditText;

import com.sha.kamel.formvalidator.util.Callback;
import com.sha.kamel.formvalidator.util.ConditionCallback;
import com.sha.kamel.formvalidator.util.Func;
import com.sha.kamel.formvalidator.util.IsValidCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sha on 11/14/17.
 */

public class ValidationOptions {
    public String emptyMessage = "Required";
    public String passwordsNotIdenticalMessage = "Passwords aren't identical.";
    public boolean shouldSkipInitialValidation;
    public Func invalidCallback;
    public List<IsValidCallback> also = new ArrayList<>();
    public List<IsValidCallback> alsoIf = new ArrayList<>();
    public List<ConditionCallback> alsoConditions = new ArrayList<>();
    public List<ConditionCallback> alsoIfConditions = new ArrayList<>();
    public List<Callback<Boolean>> alsoInvalidCallbacks = new ArrayList<>();
    public List<Callback<Boolean>> alsoIfInvalidCallbacks = new ArrayList<>();
    public List<Callback<String>> alsoErrorMessages = new ArrayList<>();
    public List<Callback<String>> alsoEtErrorMessages = new ArrayList<>();
    public List<Callback<String>> alsoIfValidatorErrorMessages = new ArrayList<>();
    public List<Callback<String>> alsoIfEtErrorMessages = new ArrayList<>();
    public List<EditText> alsoEts = new ArrayList<>();
    public List<Validator> alsoIfValidators = new ArrayList<>();
    public List<EditText> alsoIfEts = new ArrayList<>();
    public List<ConditionCallback> alsoEtConditions = new ArrayList<>();
    public List<ConditionCallback> alsoIfEtConditions = new ArrayList<>();
    public List<ConditionCallback> alsoIfValidAtorConditions = new ArrayList<>();
    public boolean shouldValidateOnChange;

    protected String emptyMessage(){
        return TextUtils.isEmpty(emptyMessage) ? null : emptyMessage;
    }

}
