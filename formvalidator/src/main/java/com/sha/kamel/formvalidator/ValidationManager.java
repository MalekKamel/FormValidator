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
    Consumer<T> subscribeWithData;
    Procedure subscribe;
    List<Validator> validators = new ArrayList<>();
    Map<TextView, ValidationBean> beans = new HashMap<>();
    Map<TextView, String> texts = new HashMap<>();
    FormValidatorMapperVa<T> mapperVa;
    FormValidatorMapper<T> mapper;
    List<Validator> passwordValidators = new ArrayList<>();
    ValidationOptions options = new ValidationOptions();

    /**
     * Call this method to start validation
     * @return this
     */
    public abstract ValidationManager startValidation();

    /**
     * Add views to start validation when
     * clicking
     * */
    public abstract ValidationManager<T> with(View... views);

    /**
     * Add one or more {@link Validator} to validate a TextView
     *
     * @param validators instances of {@link Validator}
     * @return this
     * */
    public abstract ValidationManager<T> add(Validator... validators);

    /**
     * Add a {@link Validator} to validate a TextView
     *
     * @param validator instance of {@link Validator}
     * @return this
     * */
    public abstract ValidationManager<T> add(Validator validator);

    /**
     * Call this method to map data by the index of
     * each validator you added sequentially.
     *
     * ex:
     * {@code
     *      formValidator.add(
     *                               new RangeValidator(et_name, 4, 100).initialValue("Shaban Kamel"),
     *                               new FixedLengthValidator(et_age, 2),
     *                               new MobileValidator(et_mobile),
     *                               new RangeValidator(et_area, 3, 25))
     *       .mapIndexed(texts -> new ClientInfo()
     *                               .setName(texts[0])
     *                               .setAge(texts[1])
     *                               .setMobile(texts[2])
     *                               .setArea(texts[3]))
     *
     * }
     *
     * Notice that the data will be retrieved according to the order
     * of validators you added in add method.
     * */
    /**
     *
     * @param mapper a function receiving list of strings according to the order
     *               of validators
     * @return this
     */
    public abstract ValidationManager<T> mapIndexed(FormValidatorMapperVa<T> mapper);

    /**
     * Call this method if you want to map the dat yourself
     * The library will pass you an instance of {@link FormValidator}
     * That you can use to get text of each validator easily
     *
     * ex:
     * {@code
     *     formValidator.map(validator -> new ClientInfo()
     *                         .setName(validator.textOf(et_name))
     *                         .setAge(validator.textOf(et_age))
     *                         .setMobile(validator.textOf(et_mobile))
     *                         .setArea(validator.textOf(et_area)))
     * }
     * @param mapper a function receiving an instance of {@link FormValidator}
     * @return this
     */
    public abstract ValidationManager<T> map(FormValidatorMapper<T> mapper);

    /**
     * Call this method to return {@link Observable}
     * to receive data after successful validation
     * @return this
     */
    public abstract Observable<T> asObservable();

    /**
     * Call this method if you want to test {@link FormValidator}
     * data
     * @return {@link Observable} with data
     */
    public abstract Observable<T> test();

    /**
     * Call this method to check if any field is valid.
     * @return true if any valid.
     */
    public abstract boolean isAnyValid();

    /**
     * Call this method to check if any field has text.
     * @return true if any has text.
     */
    public abstract boolean isAnyHasText();
    abstract boolean isAllValid();
    public abstract String textOf(TextView et);
    public abstract FormValidator<T> clearAll();
    boolean isAddedValidators;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /***
     * Call this method to subscribe to {@link FormValidator}
     * to receive data after successful validation
     *
     * NOTE: use this method with 'map' only.
     * @param callback a function that will receive data after successful
     *                 validation only.
     */
    public void subscribe(Consumer<T> callback){
        subscribeWithData = callback;
        prepareValidators();
    }

    /**
     * Call this method if you want to do something if
     * if the validation is true
     * @param callback method will be called on successful validation
     */
    public void subscribe(Procedure callback){
        subscribe = callback;
        prepareValidators();
    }

    /**
     * Call this method if you want to do something
     * if the validation is true
     *
     * NOTE: use this method with 'map' only.
     * @param callback method will be called on successful validation with data
     */
    public void doOnNext(Consumer<T> callback){

    }

    /**
     * Call this method if you want to do something
     * if the validation is true
     * @param callback method will be called on successful validation
     */
    public void doOnNext(Procedure callback){

    }

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
     * {@code
     * formValidator.validate(
     *                         () -> checkBox.isChecked(),
     *                         isValid ->{
     *                             if (!isValid) toast("You must accept terms and conditions!");
     *                         }
     *                 )
     * }
     * @param condition the function that validates you condition
     * @param conditionValidation function will be called with a boolean
     *                           indicating the condition is true or false
     * @return
     */
    public ValidationManager<T> validate(
            BooleanSupplier condition,
            BooleanConsumer conditionValidation
    ){
        options.validate.add(condition);
        options.validateConditionValidation.add(conditionValidation);
        return this;
    }

    /**
     * Call this method if you want to validate a condition if
     * another condition is valid.
     * ex:
     * {@code
     * .validateIf(() -> isUnder15(),
     *                         () -> cb_under15.isChecked(),
     *                         isValid -> {
     *                             if (!isValid) toast("You must confirm content is adequate for you.");
     *                         })
     * }
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
        options.validateIfConditions.add(otherCondition);
        options.validateIfCondition.add(condition);
        options.validateIfConditionValidation.add(conditionValidation);
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
        if (validators.isEmpty() && options.validate.isEmpty() && options.validateIfCondition.isEmpty())
            throw new IllegalStateException("You must use add or validate or validateIf.");

        if (mapperVa == null && mapper == null)
            throw new IllegalStateException("Must call 'map' or 'mapIndexed' before 'asObservable' or 'subscribe'.");
        
        if (toObservable == null && subscribeWithData == null && subscribe == null)
            throw new IllegalStateException("Must call 'asObservable' or 'subscribe'.");
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

        if (toObservable != null && data == null)
            throw  new IllegalStateException("'map' can't return null when using asObservable. use subscribe(Procedure) instead.");

        if (subscribeWithData != null && data == null)
            throw  new IllegalStateException("'map' can't return null when using subscribe(Consumer). use subscribe(Procedure) instead.");

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
