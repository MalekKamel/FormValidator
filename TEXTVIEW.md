TextView Validation
===================
`FormEditText` and `FormAutoCompleteTextView` are predefined `TextView` widgets 
FormValidator contains rich validators for validating ```TextView```. There're are 2 predefined widgets that inherit from `TextView`: `FormEditText` and `FormAutoCompleteTextView`

## Attributes

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

## Custom Validators
FormValidator is very flexible, it supports any custom validator. 
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
- [ ] DON'T hard-code the type like `app:customValidationType="customType"`, and add custom code as a non-translatable string in a separate value file
```xml
<!-- defined in custom_validators.xml -->
<string name="custom_validator_number_one" translatable="false">NumberOne</string>
```
and refer to it as a string as the previous example in custom validator and XML.
**Note** defining the custom like this prevents changing the value by mistake.
- [ ]  The best place to define cusom validators is Application class
```kotlin
class App : Application() {
    override fun onCreate() {
      super.onCreate()
      TextViewValidators.customValidators = listOf(NumberOneCustomValidator("Value doesn't equal 1"))
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
