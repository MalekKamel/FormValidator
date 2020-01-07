package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Padding
import androidx.ui.text.TextStyle
import com.sha.formvalidator.compose.Validatable

@Composable
fun <T: Validatable> FormContainer(
        model: T,
        recompose: () -> Unit,
        errorViewPadding: EdgeInsets = EdgeInsets(),
        children: @Composable() () -> Unit) {
    model.recompose = recompose
    Column {
        children()
        Validate(model = model, errorViewPadding = errorViewPadding)
    }
}

@Composable
fun Validate(
        model: Validatable,
        errorViewPadding: EdgeInsets = EdgeInsets()
) {
    val canValidate = model.overrideValidateOnChangeOnce || model.validateOnChange
    model.overrideValidateOnChangeOnce = false

    // tmpError is only used when calling model.showErr(), and it's removed in the first call of validate() after
    // showError() is called.
    val error = if(model.tmpError.isNotEmpty()) model.tmpError else model.errorText

    if (canValidate && !model.isValid) {
        Padding(padding = errorViewPadding) {
            Text(text = error, style = TextStyle(color = Color.Red, fontSize = 18.sp))
        }
    }
}
