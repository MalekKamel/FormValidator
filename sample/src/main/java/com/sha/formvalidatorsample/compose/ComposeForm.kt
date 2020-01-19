package com.sha.formvalidatorsample.compose

import androidx.compose.*
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.SliderPosition
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.*
import com.sha.formvalidator.compose.widget.*
import com.sha.formvalidatorsample.R

@Composable
fun ComposeForm() {

    val compositeValidation  = CompositeValidation.create<Validatable> {}

    val country = Validation.mandatory {
        errorMessage = "Country Required!"
        validateOnChange = true
        onValidate = { print("country valid = $it")}
        addTo(compositeValidation)
    }

    val email = Validation.email {
        errorTextRes = R.string.error_email_address_not_valid
        isMandatory = false
        addTo(compositeValidation)
    }

    val phone = Validation.email {
        errorTextRes = R.string.error_phone_not_valid
        addTo(compositeValidation)
    }

    val emailOrPhone = Validation.anyValid(listOf(email, phone)) {
        errorTextRes = R.string.error_email_address_not_valid
        addTo(compositeValidation)
    }

    val password = Validation.mandatory().addTo(compositeValidation)

    val confirmPassword = Validation.mandatory()
            .matches(password, compositeValidation,"Passwords don't match!")
            .addTo(compositeValidation)

    val checkBox = Validation.boolean(true) {
        validateOnChange = true
        errorMessage = "You must accept terms & conditions"
    }.addTo(compositeValidation)

    val switch = Validation.boolean(true) {
        validateOnChange = false
        errorMessage = "You must receive notifications :)"
    }.addTo(compositeValidation)

    val radioGroup = Validation.mandatory {
        errorMessage = "Select method!"
        validateOnChange = true
    }.addTo(compositeValidation)

    val slider = Validation.floatRange(0.5f, 0.9f) {
        errorMessage = "Please select within 0.5 - 0.9!"
        validateOnChange = true
    }.addTo(compositeValidation)

    val sliderPosition = SliderPosition()

    Column {
        VerticalScroller(modifier = Flexible(1f)) {
            Column {
                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(model = country, value = "USA")
                    }
                }

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(model = emailOrPhone, hint = "Email or Phone")
                    }
                }

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(
                                model = password,
                                hint = "Password"
                        )
                    }
                }

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(
                                model = confirmPassword,
                                hint = "Confirm Password"
                        )
                    }
                }

                Padding(padding = 8.dp) {
                    FormCheckBox(
                            model = checkBox,
                            text = "I accept terms & conditions.",
                            onCheckedChange = {}
                    )
                }

                Padding(padding = 8.dp) {
                    FormSwitch(
                            model = switch,
                            text = "Receive email notification.",
                            onSelected = {}
                    )
                }

                val radioOptions = listOf("Phone Call", "SMS", "Email")
                val (selectedOption, onOptionSelected) = +state { "" }

                Padding(padding = 8.dp) {
                    FormRadioGroup(
                            model = radioGroup,
                            options = radioOptions,
                            selectedOption = selectedOption,
                            onSelectedChange = onOptionSelected
                    )
                }

                Padding(padding = 8.dp) {
                    FormSlider(model = slider, position = sliderPosition)
                }

                Button(
                        text = "Login",
                        modifier = Spacing(8.dp),
                        onClick = {

                            println("Form valid = ${compositeValidation.isValid}")
//                            println("Form valid = ${ComposeValidator(country, emailOrPhone, password, checkBox, switch, radioGroup, slider).isValid}")
//                            println("Form valid = ${ComposeValidator(compositeValidation).isValid}")

                            // You can check single fields
//                            println("Country valid = ${country.isValid}")
//                            println("Email valid = ${emailOrPhone.isValid}")
//                            println("Password valid = ${password.isValid}")
//                            println("CheckBox valid = ${checkBox.isValid}")
//                            println("Switch valid = ${switch.isValid}")
//                            println("RadioGroup valid = ${radioGroup.isValid}")
//                            println("Slider valid = ${slider.isValid}")

                            // You can show error
//                            if (!checkBox.isValid)
//                                checkBox.showError("Please select this checkBox!")
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme { ComposeForm() }
}