package com.sha.kamel.formvalidator;

import android.text.TextUtils;

import com.annimon.stream.function.BooleanConsumer;
import com.annimon.stream.function.BooleanSupplier;
import com.sha.kamel.formvalidator.util.Procedure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sha on 11/14/17.
 */

public class ValidationOptions {
    public String messageIfEmpty;
    public Procedure invalidCallback;
    public List<BooleanSupplier> also = new ArrayList<>();
    public List<BooleanSupplier> alsoIf = new ArrayList<>();
    public List<BooleanSupplier> alsoIfConditions = new ArrayList<>();
    public List<BooleanConsumer> alsoInvalidCallbacks = new ArrayList<>();
    public List<BooleanConsumer> alsoIfInvalidCallbacks = new ArrayList<>();
    public boolean shouldValidateOnChange;

    public String getMessageIfEmpty(){
        return TextUtils.isEmpty(messageIfEmpty) ? "Required" : messageIfEmpty;
    }

}
