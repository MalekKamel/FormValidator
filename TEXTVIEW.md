TextView Validation
===================
`FormEditText` and `FormAutoCompleteTextView` are predefined `TextView` widgets that implement `validatable` interface.

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
There are 2 approaches to create a cutom validator

- [ ] Extend `TextValidator` to use the validator programmatically.
- [ ] Extend `CustomValidator` to use the validator in XML.

#### TextValidator

```kotlin
class SuffixValidator(private val suffix: String, errorMessage: String) : TextValidator(errorMessage) {
    override fun isValid(text: String): Boolean = text.endsWith(suffix)
}
```
Use the validator

```java
formEditText.addValidator(SuffixValidator("Must start with d."))
```
**Note** `SuffixValidator` is predefined in the library. 

#### CustomValidator
`CustomValidator` is a validator that enables you create a custom validator to be used in XML using `customValidationType` attribute.

Define the custom validator by extending `CustomValidator`.

```java
class NumberOneCustomValidator(errorMessage: String) : CustomValidator(errorMessage) {
    override fun customValidationType(context: Context): String {
    // use the type defined using non-translatable string to avoid mistakes
        return context.getString(R.string.custom_validator_number_one)
    }
    override fun isValid(text: String) = text == "1"
}
```

Declare `NumberOneCustomValidator` in XML

``` xml
<com.sha.formvalidator.widget.FormEditText
    <!-- use the type defined using non-translatable string to avoid mistakes  -->
    app:customValidationType="@string/custom_validator_number_one" 
    ...
    />
```

Register custom validators. Note that it's mandatory to register custom validators to be recognized by FormValidator.

```kotlin
class App : Application() {
    override fun onCreate() {
      super.onCreate()
      TextViewValidators.customValidators = listOf(NumberOneCustomValidator("Value doesn't equal 1"))
   }
}
```


