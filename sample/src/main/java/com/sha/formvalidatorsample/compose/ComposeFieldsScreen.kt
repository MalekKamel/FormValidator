package com.sha.formvalidatorsample.compose

import androidx.compose.Composable
import androidx.compose.State
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.ComposeFormValidator
import com.sha.formvalidator.compose.MandatoryValidation
import com.sha.formvalidator.compose.ValidatableTextModel

@Composable
fun ComposeFieldsScreen() {

    val country =+state {
        MandatoryValidation.create {
            value = "USA"
            errorMessage = "Invalid Country"
        }
    }

    val email = +state {
        MandatoryValidation.create {
            errorMessage = "Invalid Email"
        }
    }

    val password = +state {
        MandatoryValidation.create {
            errorMessage = "Invalid Email"
        }
    }

    Column {
        VerticalScroller(modifier = Flexible(1f)) {
            Column {

                Surface(border = Border(Color.Gray, 1.dp), modifier = Spacing(8.dp)) {
                    Padding(padding = 8.dp) {
                        FormTextField(country, hint = "Country")
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

                            println("Form valid = ${ComposeFormValidator(country, email, password).isValid}")
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

@Composable
fun <T: ValidatableTextModel> FormTextField(
        validatableTextModel: State<T>,
        value: String = "",
        hint: String = "",
        modifier: Modifier = Modifier.None,
        textStyle: TextStyle? = null,
        keyboardType: KeyboardType = KeyboardType.Text,
        imeAction: ImeAction = ImeAction.Unspecified,
        onFocus: () -> Unit = {},
        onBlur: () -> Unit = {},
        focusIdentifier: String? = null,
        onImeActionPerformed: (ImeAction) -> Unit = {},
        visualTransformation: VisualTransformation? = null) {
    Column {


        val inputField = @Composable {
            TextField(
                    value = validatableTextModel.value.text,
                    modifier = modifier,
                    onValueChange = { validatableTextModel.value.text = it },
                    textStyle = textStyle,
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                    onFocus = onFocus,
                    onBlur = onBlur,
                    focusIdentifier = focusIdentifier,
                    onImeActionPerformed = onImeActionPerformed,
                    visualTransformation = visualTransformation
            )
        }

        val canAddHint = hint.isNotEmpty() && validatableTextModel.value.text.isEmpty()
        Hint(hint, canAddHint, inputField)

        if (!validatableTextModel.value.isValid) {
            Text(
                    text = "required",
                    style = TextStyle(
                            color = Color.Red,
                            fontSize = 18.sp
                    )
            )
        }
    }
}

@Composable
fun Hint(
        hint: String,
        canAdd: Boolean,
        inputField: @Composable() () -> Unit
) {
    if (!canAdd) {
        inputField()
        return
    }
    val hintText =  @Composable {
        Text(
                text = hint,
                style = TextStyle(
                        fontSize = 18.sp
                )
        )
    }

    Layout(inputField, hintText) { measurable, constraints ->
        val inputfieldPlaceable = measurable[inputField].first().measure(constraints)
        val hintTextPlaceable = measurable[hintText].first().measure(constraints)
        layout(inputfieldPlaceable.width, inputfieldPlaceable.height) {
            inputfieldPlaceable.place(0.ipx, 0.ipx)
            hintTextPlaceable.place(0.ipx, 0.ipx)
        }
    }
}

enum class TextValidation {
    EMAIL, PASSWORD, REQUIRED
}


