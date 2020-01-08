/*
 * Copyright 2019 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sha.formvalidator.compose.widget

import androidx.compose.Composable
import androidx.compose.Recompose
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import com.sha.compoz.RadioGroup
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

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
                    error = model.createErrorText(),
                    errorTextArgs = errorTextArgs
            )
        }
    }
}

@Composable
private fun FormRadioGroupPreview() {
    Surface {
        val radioOptions = listOf("Calls", "Missed", "Friends")
        val (selectedOption, onOptionSelected) = +state { radioOptions[0] }
        FormRadioGroup(
                model = Validation.email(),
                options = radioOptions,
                selectedOption = selectedOption,
                onSelectedChange = onOptionSelected
        )
    }
}
