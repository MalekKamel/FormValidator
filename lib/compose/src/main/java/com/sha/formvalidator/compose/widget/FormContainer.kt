package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import com.sha.formvalidator.compose.Validatable

@Composable
fun <T: Validatable> FormContainer(
        model: T,
        recompose: () -> Unit,
        children: @Composable() () -> Unit) {
    model.recompose = recompose
    children()
}