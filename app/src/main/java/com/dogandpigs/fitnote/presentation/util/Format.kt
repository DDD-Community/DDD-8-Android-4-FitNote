package com.dogandpigs.fitnote.presentation.util

import kotlin.math.roundToInt

internal fun Int.format(): String = this.toString().format()
internal fun Double.format(): String = this.toString().format()

internal fun String.format(originalText: String = "0"): String {
    return if (this.isEmpty()) {
        "0"
    } else {
        this.toIntOrNull()?.toString()
            ?: this.toDoubleOrNull()?.let {
//                if (it % 1 == 0.0) {
//                    it.roundToInt()
//                } else {
                ((it * 100).roundToInt() / 100)
//                }
                    .toString()
            } ?: originalText
    }
}
