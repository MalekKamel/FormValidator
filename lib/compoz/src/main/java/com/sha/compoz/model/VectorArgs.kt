package com.sha.compoz.model

import androidx.ui.core.Alignment
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.graphics.BlendMode
import androidx.ui.graphics.Color
import androidx.ui.graphics.ScaleFit
import androidx.ui.layout.EdgeInsets

data class VectorArgs(
        var width: Dp = 35.dp,
        var height: Dp = 35.dp,
        var padding: EdgeInsets = EdgeInsets(right = 8.dp),
        var tintColor: Color = Color.Transparent,
        var tintBlendMode: BlendMode = BlendMode.srcIn,
        var alignment: Alignment = Alignment.Center,
        var fit: ScaleFit = ScaleFit.Fit
)