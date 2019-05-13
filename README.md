
# Android Form EditText

Powerful, simple and customizable EditText validator for Android.


## Install

#### Gradle:
```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
dependencies {
	// ...

	// ...
}
```

# Usage

```xml
<com.sha.formvalidator.widget.FormEditText
    android:id="@+id/et"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/hint_phone"
    android:inputType="phone"
    app:validationType="phone" />
```
And validate the field in your code:
``` java
    FormEditText phoneField = findViewById(R.id.et);
    if (phoneField.validate()){
	// phone is valid
	} else {
	// phone isn't valid
	}
```
## Validation Types
Validation type can be set with `validationType` attribute like the previous example `app:validationType="phone"`
**Note:** `required` is the default validation type. f you don't specify any  one

| **Type** | **Description** | **Required attributes** 
--|--|--|--|--
| **required**  | validates required fields | _ 
| **numeric**  | validates numeric only  | _
| **alpha**  | validates alpha only  | _
| **alphaNumeric**  | validates alpha numeric | _
| **email**  | validates email | _
| **creditCard**  | validates credit card <br> using [Luhn Algorithm](http://en.wikipedia.org/wiki/Luhn_algorithm) | _
| **phone**  | validates phone | _
| **domainName**  | validates domain name | _
| **ipAddress**  | validates IP address  | _
| **webUrl**  | validates web URL | _
| **personName**  | validates person name | _
| **personFullName**  | validates person full name | _
| **regex**  | validates a REGEX  | - **regex**
| **numericRange**  | validates numeric range | - **minNumber** <br> - **maxNumber**
| **floatNumericRange**  | validates floating-point ranges | - **floatMinNumber** <br> - **floatMaxNumber**
| **date**  | validates date | - **dateFormat** |



## Other Attributes

| **Attribute** | **Description** |
--|--|--|--|--
| **errorMessage**  | message if the fieldinvalid
| **requiredErrorMessage**  | message if the field is empty
| **customValidationType**  | a string for custom validation <br> (**see usage below**)

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
** Usage**
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

### See 'app' module for the full code.

# License

## Apache license 2.0