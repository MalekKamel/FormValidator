package com.sha.compoz


import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.Padding
import androidx.ui.material.RadioGroup
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import com.sha.compoz.model.TextArgs

@Composable
fun RadioGroup(
        options: List<String>,
        selectedOption: String?,
        onSelectedChange: (String) -> Unit,
        radioGroupPadding: EdgeInsets = EdgeInsets(right = 8.dp),
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp))
) {
    Column {
        Padding(padding = radioGroupPadding) {
                RadioGroup(
                        options = options,
                        selectedOption = selectedOption,
                        onSelectedChange = onSelectedChange
                )
        }

        Error(error = error, textArgs = errorTextArgs)
    }
}

@Composable
private fun RadioGroupPreview() {
    Surface {
        val radioOptions = listOf("Calls", "Missed", "Friends")
        val (selectedOption, onOptionSelected) = +state { radioOptions[0] }
        RadioGroup(
                options = radioOptions,
                selectedOption = selectedOption,
                onSelectedChange = onOptionSelected,
                error = "Error"
        )
    }
}
