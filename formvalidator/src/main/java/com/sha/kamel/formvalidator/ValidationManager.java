package com.sha.kamel.formvalidator;

import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.BooleanConsumer;
import com.annimon.stream.function.BooleanSupplier;
import com.annimon.stream.function.Consumer;
import com.sha.kamel.formvalidator.util.Procedure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Sha on 11/13/17.
 */

public abstract class ValidationManager<T> {
    PublishSubject<T> toObservable;
    Consumer<T> subscribeCallback;
    List<Validator> validators = new ArrayList<>();
    Map<TextView, ValidationBean> beans = new HashMap<>();
    Map<TextView, String> texts = new HashMap<>();
    FormValidatorMapperVa<T> mapperVa;
    FormValidatorMapper<T> mapper;
    List<Validator> passwordValidators = new ArrayList<>();
    ValidationOptions options = new ValidationOptions();

    public abstract ValidationManager startValidation();
    public abstract FormValidator<T> with(View... sourceViews);
    public abstract ValidationManager<T> add(Validator... validators);
    public abstract ValidationManager<T> add(Validator validator);
    public abstract ValidationManager<T> mapIndexed(FormValidatorMapperVa<T> mapper);
    public abstract ValidationManager<T> map(FormValidatorMapper<T> mapper);
    public abstract Observable<T> asObservable();
    public abstract Observable<T> test();
    public abstract void subscribe(Consumer<T> callback);
    public abstract boolean isAnyValid();
    public abstract boolean isAnyHasText();
    abstract boolean isAllValid();
    public abstract String textOf(TextView et);
    public abstract FormValidator<T> clearAll();
    boolean isAddedValidators;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Call this method to provide a message if any field is empty
     * The default is: Required.
     * @param messageIfEmpty message
     * @return this
     */
    public ValidationManager<T> messageIfEmpty(String messageIfEmpty){
        options.messageIfEmpty = messageIfEmpty;
        return this;
    }

    /**
     * Call this method if you want to do something
     * the validation is false, like showing {@link android.widget.Toast}
     * @param invalidCallback function will be called if invalid
     * @return this
     */
    public ValidationManager<T> doIfInvalid(Procedure invalidCallback){
        options.invalidCallback = invalidCallback;
        return this;
    }

    /**
     * Call this method if you want to validate any condition
     * ex:
     *
     * formValidator.validate(
     *                         () -> checkBox.isChecked(),
     *                         isValid ->{
     *                             if (!isValid) toast("You must accept terms and conditions!");
     *                         }
     *                 )
     * @param condition the function that validates you condition
     * @param conditionValidation function will be called with a boolean
     *                           indicating the condition is true or false
     * @return
     */
    public ValidationManager<T> validate(
            BooleanSupplier condition,
            BooleanConsumer conditionValidation
    ){
        options.also.add(condition);
        options.alsoInvalidCallbacks.add(conditionValidation);
        return this;
    }

    /**
     * Call this method if you want to validate a condition if
     * another condition is valid.
     * @param otherCondition if you return true, the condition will be validated
     * @param condition the condition that will be validated if otherCondition is valid
     * @param conditionValidation function will be called with a boolean
     *                           indicating the condition is true or false
     * @return this
     */
    public ValidationManager<T> validateIf(
            BooleanSupplier otherCondition,
            BooleanSupplier condition,
            BooleanConsumer conditionValidation
    ){
        options.alsoIfConditions.add(otherCondition);
        options.alsoIf.add(condition);
        options.alsoIfInvalidCallbacks.add(conditionValidation);
        return this;
    }

    /**
     * accept this method to validate each field while the user is
     * typing.
     * Default: false
     * @return this
     */
    public ValidationManager<T> validateOnChange(){
        options.shouldValidateOnChange = true;
        return this;
    }

    private void throwExceptionsIfFound() {
        if (validators.isEmpty() && options.also.isEmpty() && options.alsoIf.isEmpty())
            throw new IllegalStateException("You must use add or validate or validateIf.");

        if (mapperVa == null && mapper == null)
            throw new IllegalStateException("Must accept 'map' or 'mapIndexed' before 'asObservable' or 'subscribe'.");
        
        if (toObservable == null && subscribeCallback == null)
            throw new IllegalStateException("Must accept 'asObservable' or 'subscribe'.");
    }

    T getMapperData() {
        T data = null;

        if (mapper != null)
            data = mapper.call(this);
        else if (mapperVa != null){
            String[] strings = new String[validators.size()];
            for (int i = 0 ; i < strings.length ; i ++){
                strings[i] = validators.get(i).getTv().getText().toString();
            }
            data = mapperVa.call(strings);
        }

        if (data == null)
            throw  new IllegalStateException("'map' can't return null.");
        return data;
    }

    void prepareValidators(){
        throwExceptionsIfFound();

        Stream.of(validators)
                .forEach(validator -> {
                    Disposable disposable = validator.prepare(options).subscribe(bean -> {
                        beans.put(bean.getTv(), bean);
                        texts.put(bean.getTv(), bean.text());
                    });
                    compositeDisposable.add(disposable);
                    validator.emitInitialValue();
                });
    }

    /**
     * Call this method to dispose every {@link Observable} used
     * in the library to prevent memory leak.
     */
    public void dispose(){
        compositeDisposable.dispose();
    }

    ValidationManager<T> setOptions(ValidationOptions options) {
        this.options = options;
        return this;
    }
}
