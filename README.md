# android-form-validator

<a href='https://bintray.com/shabankamel/android-form-validator/android-form-validator?source=watch' alt='Get automatic notifications about new "android-form-validator" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>

[ ![Download](https://api.bintray.com/packages/shabankamel/android-form-validator/android-form-validator/images/download.svg) ](https://bintray.com/shabankamel/android-form-validator/android-form-validator/_latestVersion)

##### A simple Android form validation.
Now you can validate form easily using FormValidator.
Validating `EditText` in android was a great headache. 
But `FormValidator` do the work for you. Just few lines of code!. 
Create your validator by extending `FormValidator.Validator`
then add it to `FormValidator` instance and all is done!

# Installation

```gradle
repositories {
    jcenter() // If not already there
}
dependencies {
    compile 'com.sha.kamel:android-form-validator:0.1.0'
}
```

Usage:
###### Example 1

```java
new FormValidator.with(btn_submit)
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
                .map(validator -> new ClientInfo()
                        .setName(validator.from(et_name))
                        .setAge(validator.from(et_name))
                        .setMobile(validator.from(et_name))
                        .setArea(validator.from(et_name)))
                .doIfInvalid(() -> toast("Fill required data."))
                .asObservable()
                .doOnNext(data -> toast("Saving Client info"))
//               .flatMap(data -> {}) --> save in database
//               .flatMap(data -> {}) --> send to server
                .subscribe(
                        data -> toast("Saved data successfully."),
                        Throwable::printStackTrace);
```

###### Example 2

```java 
new FormValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
                .mapData(texts -> new ClientInfo()
                        .setName(texts[0])
                        .setAge(texts[1])
                        .setMobile(texts[2])
                        .setArea(texts[3]))
                .subscribe(data -> {
                    toast("Saved data successfully.");
                });
```

###### Example 3

```java
formValidator.with(btn_submit, () -> toast("Fill required data."))
                .add(
                        new NameValidator(et_name),
                        new AgeValidator(et_age),
                        new MobileValidator(et_mobile),
                        new AreaValidator(et_area))
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

### See 'app' module for the full code.

# License

## Apache license 2.0
