package com.sha.formvalidator.compose

import androidx.compose.*
import androidx.ui.core.*
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.Column
import androidx.ui.text.TextStyle

@Composable
fun <T: ValidatableTextModel> FormTextField(
        model: State<T>,
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
    Recompose { recompose ->
        model.value.recompose =  recompose

        Column {
            +state { model.value.text = value }
            val inputField = @Composable {
                TextField(
                        value = model.value.text,
                        modifier = modifier,
                        onValueChange = {
                            model.value.text = it
                            recompose()
                        },
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
            Hint(hint, model.value.text, inputField)
            Validate(model.value)
        }
    }
}

@Composable
fun Validate(model: ValidatableModel) {
    val canValidate = model.forceValidationOnce || model.validateOnChange
    model.forceValidationOnce = false

    if (canValidate && !model.isValid)
        Text(text = "required", style = TextStyle(color = Color.Red, fontSize = 18.sp))
}

@Composable
fun Hint(
        hint: String,
        text: String,
        inputField: @Composable() () -> Unit
) {
    if (hint.isEmpty() || text.isNotEmpty()) {
        inputField()
        return
    }
    val hintText =  @Composable {
        Text(text = hint, style = TextStyle(fontSize = 18.sp))
    }

    Layout(inputField, hintText) { measurable, constraints ->
        val inputFieldPlaceable = measurable[inputField].first().measure(constraints)
        val hintTextPlaceable = measurable[hintText].first().measure(constraints)
        layout(inputFieldPlaceable.width, inputFieldPlaceable.height) {
            inputFieldPlaceable.place(0.ipx, 0.ipx)
            hintTextPlaceable.place(0.ipx, 0.ipx)
        }
    }
}


