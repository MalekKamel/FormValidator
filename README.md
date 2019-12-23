
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

<img src="https://github.com/ShabanKamell/FormValidator/blob/dev/blob/master/raw/diagragm.png" height="600">

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
| **FormCheckBox**             |  checkBoxValidation                                |        checked         |
| **FormRatingBar**            |  ratingBarValidation                               |        required        |
| **FormSeekBar**              |  seekBarValidation                                 |        required        |
| **FormSwitch**               |  switchValidation                                  |        on              |
| **FormToggleButton**         |  toggleButtonValidation                            |        on              |


## TextView Validation
FormValidator contains rich validators for validating ```TextView```. There're are 2 predefined widgets that inherit from `TextView`: `FormEditText` and `FormAutoCompleteTextView`. see full documentation in [TextView doc](https://github.com/ShabanKamell/FormValidator/blob/dev/TEXTVIEW.md)


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
