package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.text.TextStyle
import com.sha.formvalidator.compose.Validatable

@Composable
fun <T: Validatable> FormContainer(
        model: T,
        recompose: () -> Unit,
        children: @Composable() () -> Unit) {
    model.recompose = recompose
    Column {
        children()
        Validate(model)
    }
}

@Composable
fun Validate(model: Validatable) {
    val canValidate = model.forceValidationOnce || model.validateOnChange
    model.forceValidationOnce = false

    if (canValidate && !model.isValid)
        Text(text = model.errorText, style = TextStyle(color = Color.Red, fontSize = 18.sp))
}
