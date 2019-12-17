
# FormValidator for Android

A declarative Form Validation for Android, simple, clean, and customizable.

Every time you create a form, you need to declare fields and write code for for validating each field in the form, and this results in many ```if else``` and a lot of boilerplate. For these reasons **FormValidator** is here, just declare your field in XML and its validation and all things will be done for you!

```kotlin

<com.sha.formvalidator.Form ... >
    <com.sha.formvalidator.widget.FormEditText 
    	app:validationType="email"
    ... />
    
    <com.sha.formvalidator.widget.FormCheckBox 
    	app:checkBoxValidation="checked"
    ... />
    
    <com.sha.formvalidator.widget.FormToggleButton 
    	app:toggleButtonValidation="on"
    ... />
    
    <com.sha.formvalidator.widget.FormSwitch 
    	app:switchValidation="on"
    ... />
</com.sha.formvalidator.Form>

// validate
val isFormValid = findViewById<Form>(R.id.form).validate()

```

## Installation

#### Gradle:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}

dependencies {
        // Core
        implementation 'com.github.ShabanKamell.FormValidator:core:x.y.z'
        // RxJava
        implementation 'com.github.ShabanKamell.FormValidator:rxjava:x.y.z'
}
```

(Please replace x, y and z with the latest version numbers: [![](https://jitpack.io/v/ShabanKamell/FormValidator.svg)](https://jitpack.io/#ShabanKamell/FormValidator))

## Validatable interface
FormValidator is based on ```Validatable``` interface to validate any field. If you want FormValidator to support any field, just implement ```Validatable```

#### Interface Signature

``` kotlin
interface Validatable {
    // return true if valid, false otherwise.
    fun validate(): Boolean
}
```

##### Example
``` kotlin
class CountrySpinner: AppCompatSpinner, Validatable {
// ...

// valid only if the first item is not selected (Select Country)
override fun validate(): Boolean {
  return selectedItem != adapter.getItem(0)
}

```

## Predefined Widgets
FormValidator has a collection of different widgets that implements ```Validatable``` and ready for use directly. here's the list of these widgets:

|         **Widget**           |                **Required attributes**             |      **Default**       |
| --------------------------   | -------------------------------------------------- | ---------------------  |
| **FormEditText**             |  see [TextView Validation](#textview-validation)   |        -               |
| **FormAutoCompleteTextView** |  see [TextView Validation](#textview-validation)   |        -               |
| **FormAutoCompleteTextView** |  see [TextView Validation](#textview-validation)   |        -               |
| **FormCheckBox**             |  checkBoxValidation                                |        checked         |
| **FormRatingBar**            |  ratingBarValidation                               |        required        |
| **FormSeekBar**              |  seekBarValidation                                 |        required        |
| **FormSwitch**               |  switchValidation                                  |        on              |
| **FormToggleButton**         |  toggleButtonValidation                            |        on              |


## TextView Validation
FormValidator contains rich validators for validating ```TextView```. There're are 3 predefined widgets that inherit from `TextView`: `FormEditText`, `EditTextPreference`, and `FormAutoCompleteTextView`

## TextView Validation Attributes
There are many attributes that can be used in `TextView` validation, here're all the available attributes

| **Attribute** | **Description** |
| -----------------         | --------------------------------------------------------- |
| **validationType**        | see [TextView validationType Values](#textview-validationtype-values)|
| **errorMessage**          | message if the field is invalid                           |
| **requiredErrorMessage**  | message if the field is empty                             |
| **customValidationType**  | a string for custom validation <br> (**see usage below**) |


#### TextView `validationType` Values

| **Type** | **Description** | **Required attributes** |
| ---------------------- | ----------------------------- | ----------------------------------------------- |
| **required**           | validates required fields     |  _                                              |
| **numeric**            | validates numeric only        |  _                                              |
| **alpha**              | validates alpha only          |  _                                              |
| **alphaNumeric**       | validates alpha numeric       |  _                                              |
| **email**              | validates email               |  _                                              |
| **creditCard**         | validates credit card <br> using [Luhn Algorithm](http://en.wikipedia.org/wiki/Luhn_algorithm)                                                            | _                                               |
| **phone**              | validates phone               |  _                                              |
| **domainName**         | validates domain name         |  _                                              |
| **ipAddress**          | validates IP address          |  _                                              |
| **webUrl**             | validates web URL             |  _                                              |
| **personName**         | validates person name         |  _                                              |
| **personFullName**     | validates person full name    |  _                                              |
| **regex**              | validates a REGEX             | - **regex**                                     |
| **numericRange**       | validates numeric range       | - **minNumber** <br> - **maxNumber** .          |
| **floatNumericRange**  | validates floating-point ranges | - **floatMinNumber** <br> - **floatMaxNumber**|
| **date**  | validates date                             | - **dateFormat**                                |

## Custom Validators in XML
FormValidator is very flexible. It's support any custom validator. 
Just extend `CustomValidator` and specify a unique constant
 to be used as a custom validation type:
```java
public class NumberOneCustomValidator extends CustomValidator {  
  
    public NumberOneCustomValidator(String errorMessage) {  
        super(errorMessage);  
    }  
    @Override  
    public String customValidationType(Context context) {
    // define a unique constant to be used in XML with customValidationType
        return context.getString(R.string.custom_validator_number_one);  
    }  
    @Override  
    public boolean isValid(EditText et) {
        return et.getText().toString().equals("1");  
    }  
}
```
To use `NumberOneCustomValidator` in XML put this line:
```xml
<com.sha.formvalidator.widget.FormEditText
  ...
    app:customValidationType="@string/custom_validator_number_one" 
    />
```

And tell FormValidator about  the validator before starting validation

```java
Validators.customValidators = Arrays.asList(  
        new NumberOneCustomValidator("Value doesn't equal 1") 
);
```

### Custom Validators Best Practices
- [ ] DON'T hard-code the type `app:customValidationType="customType"`
- [ ] Add custom code as a non-translatable string in a separate value file
```xml
<!-- defined in custom_validators.xml -->
<string name="custom_validator_number_one" translatable="false">NumberOne</string>
```
and refer to it as a string as the previous example in custom validator and XML.
**Note** defining the custom like this prevents changing the value by mistake.
- [ ]  The best place to define cusom validators is Application class
```java
public class App extends Application {  
  @Override  
  public void onCreate() {  
        super.onCreate();  
        Validators.customValidators = Arrays.asList(  
                new NumberOneCustomValidator("Value doesn't equal 1") // use    context.getString() in production  
        );  
  }  
}
```

## Custom Validators in Code
To add a custom validator in code just implement Validator interface

```java
public class SuffixValidator extends Validator {  
    private String suffix;  
    
    public SuffixValidator(String suffix, String errorMessage) {  
        super(errorMessage);  
       this.suffix = suffix;  
    }  
  
    public boolean isValid(EditText et) {  
        return et.getText().toString().endsWith(suffix);  
    }  
}
```
## Usage

```java
formEditText.addValidator(new SuffixValidator(/* suffix*/"d", /* error*/"Must start with d."));
```
**Note** `SuffixValidator` is predefined in the library. 

## Multiple Fields Validation
For easy multiple fields validation, you can use `FormValidator` `
```java
findViewById(R.id.btnJustCheck).setOnClickListener(__ -> {  
    boolean isValid = new FormValidator(etAllowEmpty, etAlpha, etPhone).isValid();  
    toast("Just check result: " + isValid);
});

// OR

new FormValidator(etAllowEmpty, etAlpha, etPhone)  
        .validateOnClick(  
                findViewById(R.id.btnValidateOnClick),  
                isValid -> toast("Validate on Click result: " + isValid)  
        );
```
## RX
 Instead of the previous imperative code, you can use `RxFormValidator`
```java
findViewById(R.id.btnJustCheck).setOnClickListener(__ -> {  
     new RxFormValidator(etAllowEmpty, etAlpha, etPhone)  
            .validate()  
            .subscribe(isValid -> toast("Just check result: " + isValid));
});

// OR

new RxFormValidator(etAllowEmpty, etAlpha, etPhone)  
        .validateOnClick(findViewById(R.id.btnValidateOnClick))  
        .subscribe(isValid -> toast("Validate on Click result: " + isValid));
```

## AutoCompleteTextView
You can use `FormAutoCompleteTextView` with complete validation support
```java
FormAutoCompleteTextView autoCompleteTv = findViewById(R.id.autoCompleteTv);  
if (autoCompleteTv.validate()) {  
    Toast.makeText(this, "Valid", Toast.LENGTH_LONG).show();  
}
```

## FormEditTextPreference
You can use `FormEditTextPreference` for validating EditTextPreference
```java
    <com.sha.formvalidator.widget.FormEditTextPreference
        android:ems="10"
        android:hint="@string/phone_number_hint"
        android:inputType="phone"
        android:key="phone_number"
        android:maxLines="1"
        android:selectAllOnFocus="true"
        android:title="@string/phone_number_label"
        app:required="true"
        app:validationType="phone" />
```

### See 'app' module for the full code.

### License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

### Credit
 [Android Form EditText](https://github.com/vekexasia/android-edittext-validator).
