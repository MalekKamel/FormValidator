# RxFormValidator

[ ![Download](https://api.bintray.com/packages/shabankamel/android/formvalidator/images/download.svg) ](https://bintray.com/shabankamel/android/formvalidator/_latestVersion)

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Navigator-green.svg?style=flat )]( https://android-arsenal.com/details/1/6556 )

##### A simple Android form validation.
Now you can validate form easily using FormValidator.
Validating `EditText` in android was a great headache. 
But `FormValidator` do the work for you. Just few lines of code!. 
Create your validator by extending `FormValidator.Validator`
then add it to `FormValidator` instance and all is done!

# Installation

```gradle
repositories {
        maven {
            url  "https://dl.bintray.com/shabankamel/android"
        }
    }
dependencies {
    compile 'com.sha.kamel:formvalidator:1.0.2@aar'
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
###### Example 4
If you don't want to use a view to fire validation, you can create an event:
```java
        validationEvent = formValidator.validationEvent(); // Create an event
  public void onClick(View v) {
        validationEvent.validate(); // call 'validate()' to start validation
    }
```

### Create your own validator
You can create any number of your custom validators. Just extend abstract Validator and implement
your logic.
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


### See 'app' module for the full code.

# License

## Apache license 2.0
