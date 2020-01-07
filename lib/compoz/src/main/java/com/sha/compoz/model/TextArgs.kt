package com.sha.compoz.model

import androidx.ui.core.Modifier
import androidx.ui.layout.EdgeInsets
import androidx.ui.text.ParagraphStyle
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextOverflow

data class TextArgs(
        var text: String = "",
        var padding: EdgeInsets = EdgeInsets(),
        var modifier: Modifier = Modifier.None,
        var style: TextStyle? = null,
        var paragraphStyle: ParagraphStyle? = null,
        var softWrap: Boolean = true,
        var overflow: TextOverflow = TextOverflow.Clip,
        var maxLines: Int? = null
)