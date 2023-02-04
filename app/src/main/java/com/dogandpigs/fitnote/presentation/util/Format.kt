package com.dogandpigs.fitnote.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

internal fun Date.format(): String =
    SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(this)
