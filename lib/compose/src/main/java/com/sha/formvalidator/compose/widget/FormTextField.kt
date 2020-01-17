package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.input.ImeAction
import androidx.ui.input.KeyboardType
import androidx.ui.input.VisualTransformation
import androidx.ui.text.TextStyle
import com.sha.compoz.TextField
import com.sha.compoz.model.TextArgs
import com.sha.formvalidator.compose.ValidatableModel

@Composable
fun <T: ValidatableModel<String>> FormTextField(
        model: T,
        value: String = "",
        hint: String = "",
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
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
                recompose = recompose
        ) {
            +state { model.value = value }

            TextField(
                    value = model.value ?: "",
                    hint = hint,
                    error = model.createErrorText(),
                    errorTextArgs = errorTextArgs,
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
    }
}

