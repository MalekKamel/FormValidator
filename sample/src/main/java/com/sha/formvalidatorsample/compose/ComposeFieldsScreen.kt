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
import com.sha.formvalidator.compose.ComposeValidator
import com.sha.formvalidator.compose.CompositeValidation
import com.sha.formvalidator.compose.Validatable
import com.sha.formvalidator.compose.Validation
import com.sha.formvalidator.compose.widget.*
import com.sha.formvalidatorsample.R


@Composable
fun ComposeFieldsScreen() {

    val compositeValidation = CompositeValidation<Validatable>()

    val country = Validation.mandatory(compositeValidation) {
        errorText = "Country Required!"
        validateOnChange = true
    }

    val email = Validation.email(compositeValidation) {
        errorTextRes = R.string.error_email_address_not_valid
    }

    val password = Validation.mandatory(compositeValidation)

    val checkBox = Validation.boolean(true, compositeValidation) {
        validateOnChange = true
        errorText = "You must accept terms & conditions"
    }

    val switch = Validation.boolean(true, compositeValidation) {
        validateOnChange = false
        errorText = "You must receive notifications :)"
    }

    val radioGroup = Validation.mandatory(compositeValidation) {
        errorText = "Select method!"
        validateOnChange = true
    }

    val slider = Validation.floatNumericRange(0.5, 0.9, compositeValidation) {
        errorText = "Please select within 0.5 - 0.9!"
        validateOnChange = true
    }

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
                        FormTextField(model = email, hint = "Email")
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
                val (_, onOptionSelected) = +state { radioOptions[0] }

                Padding(padding = 8.dp) {
                    FormRadioGroup(
                            model = radioGroup,
                            options = radioOptions,
                            selectedOption = null,
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
                            println("Country valid = ${country.isValid}")
                            println("Email valid = ${email.isValid}")
                            println("Password valid = ${password.isValid}")
                            println("CheckBox valid = ${checkBox.isValid}")
                            println("Switch valid = ${switch.isValid}")
                            println("RadioGroup valid = ${radioGroup.isValid}")

                            println("Form valid = ${ComposeValidator(country, email, password, checkBox).isValid}")
                            println("Form valid = ${ComposeValidator(compositeValidation).isValid}")
                            println("Form valid = ${compositeValidation.isValid}")

                            // You can show error
                            if (!checkBox.isValid)
                                checkBox.showError("Please select this checkBox!")
                        }
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme { ComposeFieldsScreen() }
}