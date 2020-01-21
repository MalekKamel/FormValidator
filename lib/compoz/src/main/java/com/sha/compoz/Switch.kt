package com.sha.compoz


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Switch
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import com.sha.compoz.model.TextArgs
import com.sha.compoz.model.ContainerArgs

@Composable
fun Switch(
        checked: Boolean,
        text: String = "",
        textArgs: TextArgs = TextArgs(),
        switchArgs: ContainerArgs = ContainerArgs(),
        modifier: Modifier = Modifier.None,
        error: String? = null,
        errorTextArgs: TextArgs = TextArgs(style = TextStyle(color = Color.Red, fontSize = 18.sp)),
        onCheckedChange: ((Boolean) -> Unit)? = null
) {
    Column {
        Clickable(onClick = { onCheckedChange?.invoke(!checked) }) {
                Row(arrangement = Arrangement.Center) {
                    Padding(padding = switchArgs.padding) {
                        Container(modifier = modifier wraps Size(switchArgs.width, switchArgs.height) wraps Gravity.Center) {
                            Switch(
                                    checked = checked,
                                    onCheckedChange = onCheckedChange
                            )
                        }
                    }
                    if (textArgs.modifier == Modifier.None) textArgs.modifier = Gravity.Center
                    Text(text = text,
                            modifier = textArgs.modifier,
                            style = textArgs.style,
                            paragraphStyle = textArgs.paragraphStyle,
                            softWrap = textArgs.softWrap,
                            overflow = textArgs.overflow,
                            maxLines = textArgs.maxLines)
            }
        }
        Error(error = error, textArgs = errorTextArgs)
    }
}

@Preview("Off")
@Composable
fun ToggleButtonPreviewOff() {
    ToggleButtonPreviewTemplate(false)
}

@Preview("On")
@Composable
fun ToggleButtonPreviewOn() {
    ToggleButtonPreviewTemplate(true)
}

@Composable
private fun ToggleButtonPreviewTemplate(selected: Boolean) {
    Surface {
        Switch(
                checked = selected,
                text = "Toggle Button",
                modifier = Spacing(32.dp)
        )
    }
}
