# RxFormValidator

[ ![Download](https://api.bintray.com/packages/shabankamel/android/formvalidator/images/download.svg) ](https://bintray.com/shabankamel/android/formvalidator/_latestVersion)

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Navigator-green.svg?style=flat )]( https://android-arsenal.com/details/1/6556 )

##### A simple Android form validation.
Now you can validate form easily using FormValidator.
Validating `EditText` in android was a great headache. 
But `FormValidator` do the work for you. Just few lines of code!. 
Create your validator by extending `FormValidator.Validator`
then add it to `FormValidator` instance and all is done!

Common validators are made for you like: `EmailValidator`, `PassordIdenticalValidator`, `RequiredValidator`
, `RangeValidator` and more.

# Installation

```gradle
repositories {
        maven {
            url  "https://dl.bintray.com/shabankamel/android"
        }
    }
dependencies {
    compile 'com.sha.kamel:formvalidator:2.6.4@aar'
}
```

# Usage:
###### Example 1

```java
  formValidator.with(btn_submit)
                .add(
                        new RangeValidator(et_name, 4, 100), // Predefined validator
                        new FixedLengthValidator(et_age, 2), // Predefined validator
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25)) // Predefined validator
                .map(validator -> new ClientInfo()
                        .setName(validator.from(et_name))
                        .setAge(validator.from(et_name))
                        .setMobile(validator.from(et_name))
                        .setArea(validator.from(et_name)))
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
                //.flatMap(data -> {}) --> save in database
                //.flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
```

###### Example 2

```java 
  formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new RangeValidator(et_name, 4, 100).initialValue("Shaban Kamel"),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .mapData(texts -> new ClientInfo()
                        .setName(texts[0])
                        .setAge(texts[1])
                        .setMobile(texts[2])
                        .setArea(texts[3]))
                .subscribe(data -> toast("Saved data successfully."));
```



###### Example 3
```java
        formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .map(validator -> new ClientInfo().setArea(validator.from(et_area)))
                .doIfInvalid(() -> toast("Form is invalid."))
                .emptyMessage("Field is empty.")
                .asObservable()
                .doOnNext(data -> Log.d(getClass().getSimpleName(), "Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);

                        
```
###### Example 4
```java
           formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new RangeValidator(et_name, 4, 100),
                        new FixedLengthValidator(et_age, 2),
                        new MobileValidator(et_mobile),
                        new RangeValidator(et_area, 3, 25))
                .map(validator -> new ClientInfo().setArea(validator.from(et_area)))
                .validateOnChange()
                .asObservable()
                .doOnNext(data -> Log.d(getClass().getSimpleName(), "Saving Client info"))
                //.flatMap(data -> {}) --> save in database
                //.flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
    }
```
###### Example 5
If you don't want to use a view to fire validation, you can create an event:
```java
  validationEvent = formValidator.validationEvent(); // Create an event
  public void onClick(View v) {
        validationEvent.validate(); // call 'validate()' to start validation
    }
```

### Can I Create my own validator?
Yes of course, you can create any number of your custom validators. Just extend abstract `Validator` and implement
your logic in `validate(String text)` method.
```java
public class MobileValidator extends Validator{

    public MobileValidator(EditText et) {
        super(et);
    }
    
    @Override
    protected boolean validate(String text) {
        if (text.length() != 11){
            errorMessage = "Mobile number must be 11 digits.";
            return false;
        }
        return true;
    }
}
```


### What if i want to validate an EditText only in a certain condition ?
You can use `Condition`. if the condition evaluates to `true`, it'll be validated. Otherwise it won't be 
validated.
```java
    new ConditionalEmailValidator(et_email, text -> checkBox.isChecked())
```
In this case, `et_email` will be validated only if `checkbox` is checked.

### Can i create my own conditional validator?
Yes of course, just let your validator implement `Conditional` interface

```java
public interface Conditional {
     Condition condition();
}
```

##### Example:
```java
public class MyConditionalValidator extends Validator implements Conditional {

    private Condition condition;

    public MyConditionalValidator(EditText et, Condition condition) {
        super(et);
        this.condition = condition;
    }

    @Override
    protected boolean validate(String text) {
        return true;
    }

    @Override
    public Condition condition() {
        return condition;
    }
}
```

### What if i have a checkbox or any condition i want to validate also?
You can use `also` to validate any condition you want.
```java
  formValidator.also(() -> checkBox.isChecked(), // This is the condition to validate
                isValid ->{ // Will be called to let you take an action according to validation state.
                    if (!isValid) toast("You must accept terms and conditions!");
                });
```
in this case, `checkbox` will be validated. If it's not checked, the result of validation will be false and 
a `Toast` will be displayed with text "You must accept terms and conditions!".

### What if i want to validate a condition only if another condition is met?
You can use `alsoIf`.
```java
formValidator.alsoIf(() -> isUnder15(), // This validation will trigger only if isUnder15 == true.
                () -> cb_under15.isChecked(),  // This is the condition to validate
                isValid -> { // Will be called to let you take an action according to validation state.
                    if (!isValid) toast("You must confirm content is adequate for you.");
                });
```
In this case, `cb_under15` will be validated only if the age is under 15.


### Can i check if any EditText has a text?
Yes, it's very easy.
```java
// Imagine it's called on back pressed
if (formValidator.isAnyHasText())
    toast("Data will be removed!");
```

### Can i delete texts in all EditText fields?
Yes, it's very easy.
```java
formValidator.clearAll()
```

### What if i want to listen to text changes in each EditTixt to implement my logic?
It's very straight forward. Just use `Validator#onChange(Callback<String>)`.
```java
new FixedLengthValidator(et_age, 2)
                .onChange(text -> {
                    if (!text.isEmpty() && Integer.valueOf(text) < 15)
                        cb_under15.setVisibility(View.VISIBLE);
                    else
                        cb_under15.setVisibility(View.GONE);
                })
```


### See 'app' module for the full code.

# License

## Apache license 2.0
