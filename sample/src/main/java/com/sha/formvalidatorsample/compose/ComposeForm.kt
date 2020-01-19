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

enum class FormTag(val value: String) {
    CHECKBOX("checkBox")
}

@Composable
fun ComposeForm() {


    // Declare FormValidation to validate all fields using FormValidation.isValid
    val form  = FormValidation.create {}

    // Declare a new field validation and add it to FormValidation
    val country = form + ModelFactory.mandatory {
        errorMessage = "Country Required!"
        validateOnChange = true
        onValidate = { print("country valid = $it")}
    }

    val email = form + ModelFactory.email {
        errorMessageRes = R.string.error_email_address_not_valid
        isMandatory = false
    }

    val phone = form + ModelFactory.phone {
        errorMessageRes = R.string.error_phone_not_valid
    }

    val emailOrPhone = form + ModelFactory.anyValid(listOf(email, phone)) {
        errorMessageRes = R.string.error_email_address_not_valid
    }

    val password = form + ModelFactory.mandatory { validateOnChange = true }

    val confirmPassword = ModelFactory.mandatory { validateOnChange = true }
            .matches(password,"Passwords don't match!")
            .addTo(form)

    val checkBox = ModelFactory.boolean(true) {
        tag = FormTag.CHECKBOX.value
        validateOnChange = true
        errorMessage = "You must accept terms & conditions"
        form + this
    }

    val switch = ModelFactory.boolean(true) {
        validateOnChange = false
        errorMessage = "You must receive notifications :)"
        shouldIgnore = { !form.modelByTag(FormTag.CHECKBOX.value)!!.isValid }
        form + this
    }

    val radioGroup = ModelFactory.mandatory {
        errorMessage = "Select method!"
        validateOnChange = true
        form + this
    }

    val slider = ModelFactory.floatRange(0.5f, 0.9f) {
        errorMessage = "Please select within 0.5 - 0.9!"
        validateOnChange = true
        form + this
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
                            val isFormValid = form.isValid
                            println("Form valid = $isFormValid")

                            /*
                            OR >> validate using ComposeValidator

                           val isFormValid = ComposeValidator(
                                    country,
                                    emailOrPhone,
                                    password,
                                    checkBox,
                                    switch,
                                    radioGroup,
                                    slider
                            ).isValid

                            OR >> using ComposeValidator with FormValidation
                            val isFormValid = ComposeValidator(form).isValid
                            */

                            // You can check single fields
                            // {country.isValid}

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
    MaterialTheme { ComposeForm() }
}