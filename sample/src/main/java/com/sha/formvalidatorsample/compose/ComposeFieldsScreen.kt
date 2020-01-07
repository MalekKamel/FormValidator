package com.sha.formvalidatorsample.compose

import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.*
import com.sha.formvalidator.compose.widget.FormCheckBox
import com.sha.formvalidator.compose.widget.FormTextField
import com.sha.formvalidator.compose.widget.FormToggleButton
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

    val toggleButton = Validation.boolean(true, compositeValidation) {
        validateOnChange = false
        errorText = "You must receive notifications :)"
    }

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
                                hint = "Password",
                                errorTextArgs = TextArgs(padding = EdgeInsets(top = 5.dp))
                        )
                    }
                }

                Padding(padding = 8.dp) {
                    FormCheckBox(
                            model = checkBox,
                            text = "I accept terms & conditions.",
                            vectorImage = +vectorResource(R.drawable.ic_add_preview),
                            onSelected = {
                                CheckBoxStatus.checked = !CheckBoxStatus.checked
                            },
                            selected = CheckBoxStatus.checked
                    )
                }

                Padding(padding = 8.dp) {
                    FormToggleButton(
                            model = toggleButton,
                            text = "Receive email notification.",
                            onSelected = {
                                ToggleButtonStatus.checked = !ToggleButtonStatus.checked
                            },
                            selected = ToggleButtonStatus.checked
                    )
                }

                Button(
                        text = "Login",
                        modifier = Spacing(8.dp),
                        onClick = {
                            println("Country valid = ${country.isValid}")
                            println("Email valid = ${email.isValid}")
                            println("Password valid = ${password.isValid}")
                            println("CheckBox valid = ${checkBox.isValid}")
                            println("ToggleButton valid = ${toggleButton.isValid}")

                            println("Form valid = ${ComposeValidator(country, email, password, checkBox).isValid}")
                            println("Form valid = ${ComposeValidator(compositeValidation).isValid}")
                            println("Form valid = ${compositeValidation.isValid}")

                            // You can show error
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

@Model
object CheckBoxStatus {
    var checked = false
}

@Model
object ToggleButtonStatus {
    var checked = false
}