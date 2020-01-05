package com.sha.formvalidatorsample.compose

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.ComposeValidator
import com.sha.formvalidator.compose.FormTextField
import com.sha.formvalidator.compose.MandatoryValidation

@Composable
fun ComposeFieldsScreen() {

    val country = MandatoryValidation.create {
        errorText = "Invalid Country"
        validateOnChange = true
    }

    val email = MandatoryValidation.create {
        errorText = "Invalid Email"
    }

    val password = MandatoryValidation.create {
        errorText = "Invalid Email"
    }

    Column {
        VerticalScroller(modifier = Flexible(1f)) {
            Column {
                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(country, value = "USA")
                    }
                }

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(email, hint = "Email")
                    }
                }

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(password, hint = "Password")
                    }
                }

                Button(
                        text = "Login",
                        modifier = Spacing(8.dp),
                        onClick = {
                            println("Country valid = ${country.value.isValid}")
                            println("Email valid = ${email.value.isValid}")
                            println("Password valid = ${password.value.isValid}")

                            println("Form valid = ${ComposeValidator(country, email, password).isValid}")
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

