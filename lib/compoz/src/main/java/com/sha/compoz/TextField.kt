package com.sha.compoz

import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.text.TextStyle
import com.sha.compoz.model.TextArgs

@Composable
fun TextField(
        value: String = "",
        hint: String = "",
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        modifier: Modifier = Modifier.None,
        onValueChange: (String) -> Unit = {},
        textStyle: TextStyle? = null,
        keyboardType: KeyboardType = KeyboardType.Text,
        imeAction: ImeAction = ImeAction.Unspecified,
        onFocus: () -> Unit = {},
        onBlur: () -> Unit = {},
        focusIdentifier: String? = null,
        onImeActionPerformed: (ImeAction) -> Unit = {},
        visualTransformation: VisualTransformation? = null
) {
    Column {
        Container {
            val inputField = @Composable {
                androidx.ui.core.TextField(
                        value = value,
                        modifier = modifier,
                        onValueChange = onValueChange,
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
            Hint(hint, value, inputField)
        }
        Error(error = error, textArgs = errorTextArgs)
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