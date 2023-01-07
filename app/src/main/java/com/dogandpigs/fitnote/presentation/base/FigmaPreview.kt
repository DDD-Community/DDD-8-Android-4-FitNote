package com.dogandpigs.fitnote.presentation.base

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * 360x800
 * mdpi 160 dpi
 */
@Preview(
    device = Devices.DEFAULT,
    widthDp = 360,
    heightDp = 800,
    showBackground = true,
)
annotation class FigmaPreview

/**
 * wrap_content
 */
@Preview(
    device = Devices.DEFAULT,
    widthDp = 360,
    showBackground = true,
)
annotation class ComponentPreview
