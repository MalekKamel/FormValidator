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
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.foundation.selection.Toggleable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.border.DrawBorder
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.sha.formvalidator.compose.R
import com.sha.formvalidator.compose.ValidatableModel
import com.sha.formvalidator.compose.Validation

@Composable
fun <T: ValidatableModel<Boolean>> FormCheckBox(
        model: T,
        vectorImage: VectorAsset,
        modifier: Modifier = Modifier.None,
        onSelected: ((Boolean) -> Unit)? = null,
        selected: Boolean = false
) {
    Recompose {recompose ->
        FormContainer(model = model, recompose = recompose) {
            Ripple(bounded = false) {
                Toggleable(
                        value = model.value,
                        onValueChange = {
                            model.value = it
                            onSelected?.invoke(it)
                        }) {
                    Container(modifier = modifier wraps Size(36.dp, 36.dp)) {
                        if (selected) {
                            DrawCheckBoxOn(vectorImage)
                        } else {
                            DrawCheckBoxOff(vectorImage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawCheckBoxOn(vectorImage: VectorAsset) {
    DrawShape(
            shape = CircleShape,
            color = (+MaterialTheme.colors()).primary
    )
    DrawVector(vectorImage)
}

@Composable
private fun DrawCheckBoxOff(vectorImage: VectorAsset) {
    val borderColor = ((+MaterialTheme.colors()).onSurface).copy(alpha = 0.12f)
    DrawBorder(
            shape = CircleShape,
            border = Border(borderColor, 2.dp)
    )
    DrawVector(
            vectorImage = vectorImage,
            tintColor = (+MaterialTheme.colors()).primary
    )
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
private fun CheckBoxPreviewTemplate(selected: Boolean) {
    Surface {
        FormCheckBox(
                model = Validation.boolean(true),
                vectorImage = +vectorResource(R.drawable.ic_add_preview),
                modifier = Spacing(32.dp),
                selected = selected
        )
    }
}
