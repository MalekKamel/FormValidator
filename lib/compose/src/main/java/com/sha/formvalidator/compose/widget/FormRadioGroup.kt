package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.RadioGroup
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.ModelFactory

@Composable
fun <T: ValidatableModel<String>> FormRadioGroup(
        model: T,
        options: List<String>,
        selectedOption: String?,
        onSelectedChange: (String) -> Unit,
        vectorArgs: VectorArgs = VectorArgs(width = 25.dp, height = 25.dp),
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            RadioGroup(
                    options = options,
                    selectedOption = selectedOption,
                    onSelectedChange = {
                        model.value = it
                        onSelectedChange(it)
                    },
                    vectorArgs = vectorArgs,
                    error = model.createError(),
                    errorTextArgs = errorTextArgs
            )
        }
    }
}

@Preview
@Composable
private fun FormRadioGroupPreview() {
    Surface {
        val radioOptions = listOf("Calls", "Missed", "Friends")
        val (selectedOption, onOptionSelected) = +state { radioOptions[0] }
        FormRadioGroup(
                model = ModelFactory.email(),
                options = radioOptions,
                selectedOption = selectedOption,
                onSelectedChange = onOptionSelected
        )
    }
}
