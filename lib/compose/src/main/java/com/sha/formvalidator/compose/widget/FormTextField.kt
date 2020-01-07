package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.EdgeInsets
import androidx.ui.text.TextStyle
import com.sha.formvalidator.compose.TextArgs
import com.sha.formvalidator.compose.ValidatableModel

@Composable
fun <T: ValidatableModel<String>> FormTextField(
        model: T,
        value: String = "",
        hint: String = "",
        errorTextArgs: TextArgs = TextArgs(),
        modifier: Modifier = Modifier.None,
        textStyle: TextStyle? = null,
        keyboardType: KeyboardType = KeyboardType.Text,
        imeAction: ImeAction = ImeAction.Unspecified,
        onFocus: () -> Unit = {},
        onBlur: () -> Unit = {},
        focusIdentifier: String? = null,
        onImeActionPerformed: (ImeAction) -> Unit = {},
        visualTransformation: VisualTransformation? = null
) {
    Recompose {recompose ->
        FormContainer(
                model = model,
                recompose = recompose,
                errorTextArgs = errorTextArgs) {
            +state { model.value = value }
            val inputField = @Composable {
                TextField(
                        value = model.value,
                        modifier = modifier,
                        onValueChange = {
                            model.value = it
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
            Hint(hint, model.value, inputField)
        }
    }
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
    val hintText =  @Composable { Text(text = hint, style = TextStyle(fontSize = 18.sp)) }

    Layout(inputField, hintText) { measurable, constraints ->
        val inputFieldPlaceable = measurable[inputField].first().measure(constraints)
        val hintTextPlaceable = measurable[hintText].first().measure(constraints)
        layout(inputFieldPlaceable.width, inputFieldPlaceable.height) {
            inputFieldPlaceable.place(0.ipx, 0.ipx)
            hintTextPlaceable.place(0.ipx, 0.ipx)
        }
    }
}