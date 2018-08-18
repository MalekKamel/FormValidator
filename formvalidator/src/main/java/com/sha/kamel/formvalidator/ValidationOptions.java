package com.sha.kamel.formvalidator;

import android.text.TextUtils;

import com.annimon.stream.function.BooleanConsumer;
import com.annimon.stream.function.BooleanSupplier;
import com.annimon.stream.function.Consumer;
import com.sha.kamel.formvalidator.util.Procedure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sha on 11/14/17.
 */

public class ValidationOptions {
    public String messageIfEmpty;
    public Procedure invalidCallback;
    public List<BooleanSupplier> validate = new ArrayList<>();
    public List<BooleanSupplier> validateIfCondition = new ArrayList<>();
    public List<BooleanSupplier> validateIfConditions = new ArrayList<>();
    public List<BooleanConsumer> validateConditionValidation = new ArrayList<>();
    public List<BooleanConsumer> validateIfConditionValidation = new ArrayList<>();

    public List<BooleanConsumer> doOnNext = new ArrayList<>();
    public List<BooleanConsumer> doOnNextWithData = new ArrayList<>();



    public boolean shouldValidateOnChange;

    public String getMessageIfEmpty(){
        return TextUtils.isEmpty(messageIfEmpty) ? "Required" : messageIfEmpty;
    }

}
