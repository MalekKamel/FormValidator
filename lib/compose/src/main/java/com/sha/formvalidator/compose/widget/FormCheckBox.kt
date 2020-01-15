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
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.graphics.Color
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.CheckBox
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.VectorArgs
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<Boolean>> FormCheckBox(
        model: T,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        vectorArgs: VectorArgs = VectorArgs(width = 25.dp, height = 25.dp),
        modifier: Modifier = Modifier.None,
        onCheckedChange: ((Boolean) -> Unit)? = null
) {
    Recompose { recompose ->
        FormContainer(
                model = model,
                recompose = recompose
        ) {
            CheckBox(
                    text = text,
                    checked = model.value,
                    textArgs = textArgs,
                    vectorArgs = vectorArgs,
                    modifier = modifier,
                    error = model.createErrorText(),
                    errorTextArgs = errorTextArgs,
                    onCheckedChange = {
                        model.value = it
                        onCheckedChange?.invoke(it)
                    }
            )
        }
    }
}

@Preview("Off")
@Composable
fun CheckBoxPreviewOff() {
    CheckBoxPreviewTemplate(false)
}

@Preview("On")
@Composable
fun CheckBoxPreviewOn() {
    CheckBoxPreviewTemplate(true)
}

@Composable
private fun CheckBoxPreviewTemplate(checked: Boolean) {
    Surface {
        FormCheckBox(
                model = Validation.boolean(checked),
                text = "Check Box",
                modifier = Spacing(32.dp)
        )
    }
}
